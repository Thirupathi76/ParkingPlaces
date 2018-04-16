package com.vmax.parkingplaces;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
 * Created by user on 27-Dec-17.
 */

public class FeedbackFragment extends Fragment implements View.OnClickListener {

    private static final int SELECT_FILE = 152;
    private static final int REQUEST_CAMERA = 251;
    View mRootview;
    EditText et_name,et_email,et_message,et_mobile;
    Button btn_submit;
    String et_name_str,et_email_str,et_message_str,imei="000000000000000",et_mobile_str;
    String currentLanguage;
    Configuration config;
    Context context;
    Locale locale;
    ApiService mAPIService;
    ImageView imageView;
    Uri uri;
    File photoPath;
    private String userChoosenTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootview=inflater.inflate(R.layout.feedback_layout, null);

        context=getActivity();

        photoPath = new File("");
        imageView = mRootview.findViewById(R.id.imageView);
        et_name=(EditText)mRootview.findViewById(R.id.et_name);
        et_email=(EditText)mRootview.findViewById(R.id.et_email);
        et_message=(EditText)mRootview.findViewById(R.id.et_message);
        et_mobile=(EditText)mRootview.findViewById(R.id.et_mobile);

        btn_submit=(Button)mRootview.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        imageView.setOnClickListener(this);
        return mRootview;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_submit) {
            et_name_str = et_name.getText().toString();
            et_name_str = et_name_str.replace(" ", "%20");
            et_email_str = et_email.getText().toString();
            et_email_str = et_email_str.replace(" ", "%20");
            et_message_str = et_message.getText().toString();
            et_message_str = et_message_str.replace(" ", "%20");
            et_mobile_str = et_mobile.getText().toString();

            if (validation()) {

                try {

                    sendPost(et_name_str, et_email_str, et_message_str, et_mobile_str, imei);

                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else if (v.getId() == R.id.imageView){
            selectImage();
        }
    }
    private boolean validation() {
        boolean result=true;
        et_name_str=et_name.getText().toString();
        et_email_str=et_email.getText().toString();
        et_mobile_str=et_mobile.getText().toString();

        if(et_name_str.isEmpty())
        {
            et_name.setError("Enter Name");
            result=false;
        }
        if(et_mobile_str.length()==0||!isMobileNumber(et_mobile_str)){
            result=false;
            et_mobile.setError("Enter valid mobile number");
        }

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(et_email_str.matches(emailPattern) && et_email_str.length() > 0)
        {

        }
        else
        {
            result=false;
            et_email.setError("Enter valid email id");
        }
        return result;
    }

    public boolean isMobileNumber(String number){

        if((number.startsWith("9")||number.startsWith("8")||number.startsWith("7"))&&number.length()==10)return true;
        return false;

    }

    private void dialogbox(String msg) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("ALert!");
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               et_name.setText("");
               et_email.setText("");
               et_message.setText("");
               et_mobile.setText("");
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void sendPost(String name, String email, String message, String mobile, String imei) {


        mAPIService = RetroClient.sendFeedback();

        /*String filePath = getRealPathFromURIPath(uri, context);
        File file = new File(filePath);*/

        Log.d("File Upload", "Filename " + photoPath.getName());
        //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), photoPath);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("photo", photoPath.getName(), mFile);
//        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Log.e("Details", name+" , "+email+" , "+message+" , "+mobile+" , "+imei+" , "+photoPath.getAbsolutePath());

        Call<ResponseBody> call = mAPIService.savePost(name,"1","23",email,mobile,message,imei,
                "17.5555","78.23232","02-01-2018", fileToUpload);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("FeedbackPojo", "FeedbackPojo submitted to API." + response.body().toString());
                dialogbox("Success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("FeedbackPojo", "Unable to submit FeedbackPojo to API.");
            }
        });


       /* mAPIService.savePost(name, email, message, mobile, imei, new Callback<Response>() {

            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {
                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.e("FeedbackPojo", "FeedbackPojo submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("FeedbackPojo", "Unable to submit FeedbackPojo to API.");

            }
        });*/


    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Gallery",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {
                    userChoosenTask="Take Photo";

                        cameraIntent();
                } else if (items[item].equals("Choose from Gallery")) {
                    userChoosenTask="Choose from Gallery";

                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);

    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private String getRealPathFromURIPath(Uri contentURI, Context activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                uri = data.getData();
//                Log.e("gallery URI", uri.toString());
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(thumbnail);
                photoPath = createTempImageFile(thumbnail);
//                Bitmap bit = Bitmap.createScaledBitmap(thumbnail,100,100,true);
            }
            else if (requestCode == SELECT_FILE) {
                uri = data.getData();
                Log.e("Camera URI", uri.toString());
//                imageView.setImageURI(uri);
                InputStream image_stream = null;
                try {
                    image_stream = getActivity().getContentResolver().openInputStream(uri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Bitmap bitmap= BitmapFactory.decodeStream(image_stream );
                imageView.setImageBitmap(bitmap);
                photoPath = createTempImageFile(bitmap);
//                Bitmap bit = Bitmap.createScaledBitmap(bitmap,100,100,true);

            }
        }
    }

    public static File createTempImageFile(Bitmap inImage) {

        //  Log.e("Add to Disk",imageName);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        //File dirPath=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+File.separator+DirectoryName);
        File mediaDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Pictures");
        if(!mediaDirectory.exists())mediaDirectory.mkdirs();
        String timeStamp = new SimpleDateFormat("ddMMMyyyy_HHmmss", Locale.getDefault()).format(new Date());
        File filePath=new File(mediaDirectory.getPath()+File.separator+timeStamp+".jpg");

        if(!filePath.exists()) {
            try {
                filePath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStream os=null;
        try {
            os= new FileOutputStream(filePath.getPath());
            os.write(bytes.toByteArray());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    public void showResponse(String response) {
        if (!response.equals(""))
            Toast.makeText(context, "Response"+response, Toast.LENGTH_SHORT).show();
    }
}
