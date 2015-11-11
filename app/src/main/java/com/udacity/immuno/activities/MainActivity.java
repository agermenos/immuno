package com.udacity.immuno.activities;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.udacity.immuno.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.navigation_view) NavigationView mNavigationView;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        setUpToolbar();
        setUpNavigation();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO: set title based on user’s first name
        getSupportActionBar().setTitle("Alejandro’s Record");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
    }

    private void setUpNavigation() {

        //TODO: set up account information from db

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_item_parent1:
                        //TODO: create new fragment
                        break;

                    case R.id.nav_item_parent2:
                        //TODO: create new fragment
                        break;
                    case R.id.nav_item_child1:
                        //TODO: create new fragment
                        break;

                    case R.id.nav_item_child2:
                        //TODO: create new fragment
                        break;
                } //switch

                //TODO: add fragment to FrameLayout
                mDrawerLayout.closeDrawer(GravityCompat.START);

                return true;
            } //onNavigationItemSelected
        });
    } //setUpNavigation

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_share:
                saveUserVaccineInfoAsPDF();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveUserVaccineInfoAsPDF() {
        // Create a new object of PdfDocument
        final PdfDocument document = new PdfDocument();

        //To Do: Dummy content for PDF, replace it with the vaccine information
        // Need to fix it with real content
        final View content = findViewById(R.id.dummy_textview);

        //PDF Document Page Number
        int pageNumber = 1;

        //A4 page for PDF document
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(
                4960, 7016, pageNumber).create();

        // Create a new page with the pageInfo
        PdfDocument.Page pdfPage = document.startPage(pageInfo);
        content.draw(pdfPage.getCanvas());

        //finish creating the document
        document.finishPage(pdfPage);

        //Save created document to sd card
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy_hhss");
        String pdfName = "ImmunoPDF_"
                + dateFormat.format(Calendar.getInstance().getTime()) + ".pdf";

        if(!isExternalStorageWritable()){
            String strStorageNotWritableError = "The External Storage is not available.";
            ImmunoToast(strStorageNotWritableError);
        }

        File outputDirectory = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getPath() + "/Immuno");

        if(!outputDirectory.exists()){
            outputDirectory.mkdir();
        }

        try {
            File OutputFile = new File(outputDirectory + "/" + pdfName);
            OutputFile.createNewFile();
            OutputStream out = new FileOutputStream(OutputFile);
            document.writeTo(out);
            document.close();
            out.close();
            String strImmunoPDFCreated = "Exported pdf at " + OutputFile;
            ImmunoToast(strImmunoPDFCreated);
        } catch (IOException e) {
            e.printStackTrace();
            String strImmunoPDFError = "Could not create " + pdfName + ". Please " +
                    "check the SD Card permission in settings";
            ImmunoToast(strImmunoPDFError);
        }

}

    private void ImmunoToast(String strText) {
        Toast immunoToast = Toast.makeText(this, strText, Toast.LENGTH_LONG);
        immunoToast.show();
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
