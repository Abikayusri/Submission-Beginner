package abika.sinaudicodingjava.submission_hokage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import abika.sinaudicodingjava.submission_hokage.model.Hokage;

public class DescriptionActivity extends AppCompatActivity {
    public static final String EXTRA_HOKAGE = "extra_hokage";

    ImageView imgPhoto;
    TextView tvName;
    TextView tvTitle;
    TextView tvBio;
    TextView tvBorn;
    TextView tvDie;
    TextView tvJutsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        imgPhoto = findViewById(R.id.img_desc_photo);
        tvName = findViewById(R.id.tv_desc_name);
        tvTitle = findViewById(R.id.tv_desc_title);
        tvBio = findViewById(R.id.tv_desc_content_text);
        tvBorn = findViewById(R.id.tv_desc_lahir_value);
        tvDie = findViewById(R.id.tv_desc_wafat_value);
        tvJutsu = findViewById(R.id.tv_desc_jutsu_value);

        Hokage hokage = getIntent().getParcelableExtra(EXTRA_HOKAGE);

        String name = hokage.getName();
        String title = hokage.getTitle();
        String bio = hokage.getBio();
        String born = hokage.getBorn();
        String die = hokage.getDie();
        String jutsu = hokage.getJutsu();

        Glide.with(this)
                .load(hokage.getPhoto())
                //placeholder berfungsi menampilkan gambar sebelum dimuat
                .placeholder(R.drawable.placeholder)
                //error menampilkan gambar jika terjadi eror saat load gambar dari url
                .error(R.drawable.notfound)
                .apply(new RequestOptions().override(350, 550))
                .into(imgPhoto);

        tvName.setText(name);
        tvTitle.setText(title);
        tvBio.setText(bio);
        tvBorn.setText(born);
        tvDie.setText(die);
        tvJutsu.setText(jutsu);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Hokage Desa Konohagakure");
        }
    }
}
