package br.rv.wf;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvc.imagepicker.ImagePicker;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppActivity extends AppCompatActivity {
    @BindView(R.id.image_view)   ImageView imageView;
    @BindView(R.id.image_stream_indicator )  TextView textView;
    @BindView(R.id.login_email) EditText login_email;
    @BindView(R.id.login_password ) EditText login_password;
    Context contexto;
    Bitmap bitmap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        contexto = this;
        ButterKnife.bind(this);
//        imageView = (ImageView) findViewById(R.id.image_view);
//        textView = (TextView) findViewById(R.id.image_stream_indicator);
        ImagePicker.setMinQuality(600, 600);

    }
    @OnClick
    public void  login(){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        InputStream is = ImagePicker.getInputStreamFromResult(this, requestCode, resultCode, data);
        if (is != null) {
            textView.setText("Got input stream!");
            try {
                is.close();
            } catch (IOException ex) {
                // ignore
            }
        } else {
            textView.setText("Failed to get input stream!");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onPickImage(View view) {
        ImagePicker.pickImage(this, "Selecione sua imagem:");
    }


}
