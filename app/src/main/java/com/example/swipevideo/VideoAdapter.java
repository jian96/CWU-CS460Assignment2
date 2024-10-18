package com.example.swipevideo;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<VideoItem> videoItems;

    public VideoAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }

    /**
     * This method is claled when creating a ViewHolder
     * Instantiates a ViewHolder object based on input from an XML file
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video,parent,false)
        );
    }

    /**
     * This method is called when a ViewHolder is recycled to be populated with data
     * of new videos
     * Sets the recycled ViewHolder with new video data
     * @param holder The ViewHolder to be recycled
     * @param position The position of the new video in the adapter's array
     */
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position));

    }

    /**
     * This returns how many videos are in the array
     * @return Size of the adapter's array
     */
    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder{
        TextView textVideoTitleFunc, textVideoDescriptionFunc, textVideoIDFunc;
        VideoView videoViewFunc;
        ProgressBar progressBar;

        /**
         * This method associates variables to represent an associated View
         * @param itemView The associated View element
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoViewFunc = itemView.findViewById(R.id.videoView);
            textVideoDescriptionFunc = itemView.findViewById(R.id.textVideoDescription);
            textVideoTitleFunc = itemView.findViewById(R.id.textVideoTitle);
            textVideoIDFunc = itemView.findViewById(R.id.textVideoID);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        /**
         * This method associates a View object to a video (videoItem)
         * so that the View object displays data from the video
         * This method also appends an "ID" label to the raw ID value for display
         * @param videoItem The video data we are retrieving information from
         */
        void setVideoData(VideoItem videoItem){
            String videoIDwLabel = "ID: " + videoItem.videoID;
            textVideoTitleFunc.setText(videoItem.videoTitle);
            textVideoDescriptionFunc.setText(videoItem.videoDescription);
            textVideoIDFunc.setText(videoIDwLabel);
            videoViewFunc.setVideoPath(videoItem.videoURL);

            videoViewFunc.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                /**
                 * This method is called when a video is prepared
                 * It disables visbility of the progress bar and scales the video to be
                 * fit for display on the device
                 * @param mp The MediaPlayer object used to play the video
                 */
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);
                    mp.start();

                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float screenRatio = videoViewFunc.getWidth()/(float) videoViewFunc.getHeight();

                    float scale = videoRatio / screenRatio;
                    if (scale >= 1f){
                        videoViewFunc.setScaleX(scale);
                    } else {
                        videoViewFunc.setScaleX(1f / scale);
                    }
                }
            });
            videoViewFunc.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                /**
                 * This method is called when the video is done loading, and plays the video
                 * @param mp The MediaPlayer used to play the video
                 */
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }


}
