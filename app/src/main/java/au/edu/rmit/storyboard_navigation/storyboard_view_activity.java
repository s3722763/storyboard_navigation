package au.edu.rmit.storyboard_navigation;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class storyboard_view_activity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storyboard_view_activity);
        ImageView imageView = this.findViewById(R.id.pictogram_1);
        imageView.setImageResource(R.drawable.walkpictogram);
        imageView = this.findViewById(R.id.pictogram_2);
        imageView.setImageResource(R.drawable.walkpictogram);
    }
}