package jp.androidbook.myapp.booksmap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.IOException;


public class BooksListActivity extends Activity {

    private SQLiteDatabase db;
    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_books_list, menu);
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

    @Override
    public void onStart() {
        super.onStart();

        //RegisterActivityからIntentされた中身の取得。String型配列に格納。
        Intent intent = getIntent();
        String[] regData = new String[5];


        //↓もしADDRESS(住所)が入力されてなかったら処理を実行しない。
        if(regData[1]!=null) {

            regData[0] = intent.getStringExtra("name");
            regData[1] = intent.getStringExtra("address");
            regData[2] = intent.getStringExtra("tell");
            regData[3] = intent.getStringExtra("eval");
            regData[4] = intent.getStringExtra("coment");
        }

        /**
        AlertDialog.Builder ab = new AlertDialog.Builder(BooksListActivity.this);
        ab.setTitle("In Intent");
        ab.setMessage(intent.getStringExtra("name") + "\n" + intent.getStringExtra("address") + "\n" + intent.getStringExtra("tell") + "\n" + intent.getStringExtra("eval") + "\n" + intent.getStringExtra("coment") + "\n");
        ab.show();
        **/

        //
        myListView = (ListView) findViewById(R.id.listView);

        MyDbHelper dbHelper = new MyDbHelper(this);
        db = dbHelper.getWritableDatabase();

        try {
            insertToDB(regData);

            Cursor c = searchToDB();

            String[] from = {MyDbHelper.NAME, MyDbHelper.ADDRESS, MyDbHelper.TELL, MyDbHelper.EVALUATION, MyDbHelper.COMENT};

            int[] to = {R.id.r_name, R.id.r_add, R.id.r_tell, R.id.r_eval, R.id.r_coment};

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listitem, c, from, to, 0);

            myListView.setAdapter(adapter);

            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String s1 = ((TextView) view.findViewById(R.id.r_add)).getText().toString();
                    Intent intent_add = new Intent(BooksListActivity.this, MapsActivity.class);
                    intent_add.putExtra("add", s1);
                    startActivity(intent_add);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        Button btn = (Button) findViewById(R.id.b_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BooksListActivity.this, RegisterActivity.class);

                startActivity(intent);


            }
        });
    }

    private void insertToDB(String[] data) throws Exception{
        db.execSQL("insert into myData(" + MyDbHelper.NAME + "," + MyDbHelper.ADDRESS + "," + MyDbHelper.TELL + "," + MyDbHelper.EVALUATION + "," + MyDbHelper.COMENT + ") values('"+ data[0] + "','"+ data[1] + "','"+ data[2] + "','"+ data[3] + "','"+ data[4] + "')");
    }

    private Cursor searchToDB() throws Exception {
            Cursor c = db.rawQuery("select * from " + MyDbHelper.TABLE_NAME, null);
            return c;
     }

}

