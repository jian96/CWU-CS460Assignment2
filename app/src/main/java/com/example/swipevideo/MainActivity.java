package com.example.swipevideo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final ViewPager2 videoViewPager = findViewById(R.id.videosViewPager);

        List<VideoItem> videoItemsList = new ArrayList<>();

        VideoItem videoNumOne = new VideoItem();
        videoNumOne.videoURL = "https://firebasestorage.googleapis.com/v0/b/cs460-test.appspot.com/o/videoone.mp4?alt=media&token=5ac7c6dc-1c41-4c54-8c50-17f18abc7998";
        videoNumOne.videoTitle = "Video one";
        videoNumOne.videoDescription = "This is video number one. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
        videoNumOne.videoID = "19357812";
        videoItemsList.add(videoNumOne);

        VideoItem videoNumTwo = new VideoItem();
        videoNumTwo.videoURL = "https://firebasestorage.googleapis.com/v0/b/cs460-test.appspot.com/o/videotwo.mp4?alt=media&token=412867f2-ca48-4f34-b915-db1cd1381984";
        videoNumTwo.videoTitle = "A funeral for a species";
        videoNumTwo.videoDescription = "This is video number two. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
        videoNumTwo.videoID = "68918392";
        videoItemsList.add(videoNumTwo);

        VideoItem videoNumThree = new VideoItem();
        videoNumThree.videoURL = "https://firebasestorage.googleapis.com/v0/b/cs460-test.appspot.com/o/videothree.mp4?alt=media&token=75996755-c5c8-49e7-9fa4-2174e93b86bb";
        videoNumThree.videoTitle = "Turning a Sphere Inside Out";
        videoNumThree.videoDescription = "This is video number three. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
        videoNumThree.videoID = "15819293";
        videoItemsList.add(videoNumThree);

        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));

    }
}