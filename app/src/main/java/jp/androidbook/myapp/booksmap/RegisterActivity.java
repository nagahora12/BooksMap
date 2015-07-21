package jp.androidbook.myapp.booksmap;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
    public void onStart(){
        super.onStart();

        final EditText edit_name = (EditText)findViewById(R.id.edit_name);          //名前
        final EditText edit_address = (EditText)findViewById(R.id.edit_add);        //住所
        final EditText edit_tell = (EditText)findViewById(R.id.edit_tell);          //電話番号
        final EditText edit_eval = (EditText)findViewById(R.id.edit_eval);          //評価
        final EditText edit_coment = (EditText)findViewById(R.id.edit_coment);      //一言コメント

        Button regbtn = (Button)findViewById(R.id.regbutton);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent reg_intent = new Intent(RegisterActivity.this,BooksListActivity.class);

                reg_intent.putExtra("name", edit_name.getText().toString());
                reg_intent.putExtra("address", edit_address.getText().toString());
                reg_intent.putExtra("tell", edit_tell.getText().toString());
                reg_intent.putExtra("eval", edit_eval.getText().toString());
                reg_intent.putExtra("coment", edit_coment.getText().toString());

                startActivity(reg_intent);
            }
        });
    }
}
