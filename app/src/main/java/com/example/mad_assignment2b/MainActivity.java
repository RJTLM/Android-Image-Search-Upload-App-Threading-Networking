package com.example.mad_assignment2b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerInterface {

    ImageButton loadImage;
    RecyclerView imagesRecycler;
    EditText searchKey;
    ArrayList<Bitmap> imagesList;
    ProgressBar progressBar;
    ImageAdapter imageAdapter;
    ImageButton layoutButton;

    ImageButton uploadButton;
    boolean isLinearLayout;
    FirebaseStorage storage;
    StorageReference storageReference;

    Bitmap selectedImage;
    int uploadCounter = 0;
    String API_KEY = "40091430-7072d2859219a393a14bee3a5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagesList = new ArrayList<>();

        progressBar = findViewById(R.id.progressBarId);
        loadImage = findViewById(R.id.searchButton);
        imagesRecycler = findViewById(R.id.imageRecycler);
        searchKey = findViewById(R.id.searchText);
        layoutButton = findViewById(R.id.layoutButton);
        uploadButton = findViewById(R.id.uploadButton);

        progressBar.setVisibility(View.INVISIBLE);

        imageAdapter = new ImageAdapter(imagesList, this, this);
        imagesRecycler.setLayoutManager(new LinearLayoutManager(this));
        isLinearLayout = true;
        imagesRecycler.setAdapter(imageAdapter);

        layoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isLinearLayout)
                {
                    imagesRecycler.setLayoutManager(new GridLayoutManager(
                            getBaseContext(),
                            2,
                            GridLayoutManager.VERTICAL,
                            false));
                    isLinearLayout = false;
                }
                else {
                    imagesRecycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    isLinearLayout = true;
                }
            }
        });

        loadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagesList.clear();
                String searchValues = searchKey.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                doSearch(searchValues);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedImage != null)
                {
                    storage = FirebaseStorage.getInstance();
                    storageReference = storage.getReference("Images/");
                    StorageReference imageRef = storageReference.child("image" + uploadCounter + ".jpg");
                    uploadCounter++;
                    UploadTask uploadTask = imageRef.putBytes(convertBitmap(selectedImage));
                    selectedImage = null;
                    Toast.makeText(getBaseContext(), "Image uploaded.", Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(getBaseContext(), "No image selected.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void doSearch(String searchKey) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        RemoteAPICalls remoteAPICalls = retrofit.create(RemoteAPICalls.class);
        Observable<ResponseModel> response = remoteAPICalls.getSearchResponse(API_KEY,
                searchKey);

        Disposable subscribe = response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            if (!result.getHits().isEmpty() && result.getHits().size() > 0) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(this, "Search complete, loading images.", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.VISIBLE);
                                Thread.sleep(3000);
                                selectedImage = null;
                                if(result.getHits().size() > 15)
                                {
                                    for(int i = 0; i < 15; i++)
                                    {
                                        loadImage(result.getHits().get(i).getLargeImageURL());
                                    }
                                }
                                else
                                {
                                    for(int i = 0; i < result.getHits().size(); i++)
                                    {
                                        loadImage(result.getHits().get(i).getLargeImageURL());
                                    }
                                }


                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(this, "No images found.", Toast.LENGTH_LONG).show();

                            }
                        },
                        error -> {
                            Log.d("error", "error: " + error);
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(this, "Error in internet connection.", Toast.LENGTH_LONG).show();
                        }
                );

    }

    private void loadImage(String imageEndpoint) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        RemoteAPICalls remoteAPICalls = retrofit.create(RemoteAPICalls.class);
        Observable<ResponseBody> response = remoteAPICalls.getImage(imageEndpoint);
        Disposable subscribe = response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            progressBar.setVisibility(View.INVISIBLE);
                            Bitmap bitmap = BitmapFactory.decodeStream(result.byteStream());
                            imagesList.add(bitmap);
                            imageAdapter.notifyDataSetChanged();
                        },
                        error -> {
                            Log.d("error", "error: " + error);
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(this, "Error in loading image.", Toast.LENGTH_LONG).show();
                        }
                );

    }

    @Override
    public void onItemClick(int position) {
        selectedImage = imagesList.get(position);
        Toast.makeText(getBaseContext(), "Image selected.", Toast.LENGTH_LONG).show();
    }
    public byte[] convertBitmap(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
