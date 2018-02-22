package masip.marc.editdoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DocActivity extends AppCompatActivity {

    private static final int EDIT_BODY = 1;
    private static final int EDIT_TITLE = 0;
    private TextView title;
    private TextView body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc);

        title = (TextView) findViewById(R.id.title);
        body = (TextView) findViewById(R.id.body);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.edit_title:
                edit(title.getText().toString(), EDIT_TITLE);
                break;
            case R.id.edit_body:
                edit(body.getText().toString(), EDIT_BODY);
                break;
        }
        return true;
    }

    private void edit(String text, int requestCode){
        Intent intent = new Intent(this, EditTextActivity.class);//intent ens permet transmetre info entre activitats
        intent.putExtra("text", text);//insertar text a l'intent
        startActivityForResult(intent, requestCode);//el segon parámetre es el request code (1)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case EDIT_TITLE://comprovem quin és el request code
                if (resultCode == RESULT_OK){//si ha clicat a save (i no ha tornat amb el botó back
                    String text = data.getStringExtra("text");//recollim el text de l'intent
                    title.setText(text);//setejem el text a title
                }
                break;
            case EDIT_BODY://comprovem quin és el request code
                if (resultCode == RESULT_OK){//si ha clicat a save (i no ha tornat amb el botó back
                    String text = data.getStringExtra("text");//recollim el text de l'intent
                    body.setText(text);//setejem el text a body
                }
                break;
        }
    }
}
