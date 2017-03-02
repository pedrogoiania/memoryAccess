package topicos_avancados.com.br.memoryaccess;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recuperando wiget EditText na MainActivity
        final EditText et = (EditText) findViewById(R.id.editText);

        //Criando funcao para salvar o texto do EditText no arquivo
        Button salvarArquivo = (Button) findViewById(R.id.salvarArquivo);
        salvarArquivo.setOnClickListener(new View.OnClickListener() {
            FileOutputStream fos;
            @Override
            public void onClick(View v) {
                try{
                    fos = openFileOutput("texto", Context.MODE_PRIVATE);
                    fos.write(et.getText().toString().getBytes());
                    fos.close();
                } catch(FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        //Criando funcao para ler o texto do arquivo salvo para o campo EditText

        Button lerArquivo = (Button) findViewById(R.id.lerArquivo);
        lerArquivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ch;
                StringBuffer buf = new StringBuffer();
                FileInputStream fis;

                try{
                    fis = openFileInput("texto");
                    while ((ch = fis.read()) != -1) {
                        buf.append((char) ch);
                    }
                    fis.close();
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
                et.setText(buf.toString());
            }
        });

        //Criando funcao para ler texto de outro arquivo

        Button lerEstativo = (Button) findViewById(R.id.lerEstatico);
        lerEstativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ch;
                StringBuffer buf = new StringBuffer();
                InputStream is;

                try {
                    is = getResources().openRawResource(R.raw.exemplo);
                    while ((ch = is.read()) != -1) {
                        buf.append((char) ch);
                    }
                    is.close();
                }
                catch (FileNotFoundException e) { e.printStackTrace();
                }
                catch (IOException e) { e.printStackTrace();
                }
                et.setText(buf.toString());
            }
        });

    }
}
