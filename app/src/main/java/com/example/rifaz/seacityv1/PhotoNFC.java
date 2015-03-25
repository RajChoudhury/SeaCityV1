package com.example.rifaz.seacityv1;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class PhotoNFC extends ActionBarActivity {
    private Button scanNew;
    private NfcHelper helper;
    private Button save;
    private Boolean isWritingTag;
    NfcAdapter  nfc;

    ProgressDialog writingProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new NfcHelper(this);
        nfc = NfcAdapter.getDefaultAdapter(this);
        setContentView(R.layout.activity_information_nfc);
        scanNew = (Button) findViewById(R.id.scanNew);
        save = (Button) findViewById(R.id.saveImage);
        if(!helper.isNfcEnabledDevice()){
            Toast.makeText(getApplicationContext(),"Please Enable NFC to use this function",Toast.LENGTH_LONG).show();
        } else {
            scanNew.setEnabled(true);
            save.setEnabled(true);
            showWaitingDialog();
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Saving the image",Toast.LENGTH_SHORT).show();
            }
        });

        scanNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWaitingDialog();
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_information_nfc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showWaitingDialog(){
        writingProgressDialog = ProgressDialog.show(this,"","Tap on a Tag!", false, true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                isWritingTag = false;
            }
        });
        isWritingTag = true;
    }

    public void onNewIntent(Intent intent){
        if(helper.isNfcIntent(intent)){
            toast("NFC Tag scanned");
        }
        super.onNewIntent(intent);

    }

    public void onPause(){
        super.onPause();
        nfc.disableForegroundDispatch(this);
    }

    public void onResume(){
        super.onResume();
        Intent intent = new Intent(this, PhotoNFC.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        IntentFilter[] intentFilters = new IntentFilter[] {};
        nfc.enableForegroundDispatch(this,pendingIntent,intentFilters,null);
    }

    public void toast(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}
