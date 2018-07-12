package rmk.virtusa.com.quizmaster;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import rmk.virtusa.com.quizmaster.R;

public class DashActivity extends AppCompatActivity {
    private static final String TAG = "DashActivity";

    private ArrayList<String> mTests=new ArrayList<>();
    private ArrayList<String> mDates=new ArrayList<>();
    private ArrayList<String> mTimes= new ArrayList<>();
    private ArrayList<String> mButtons =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        Log.d(TAG, "onCreate: started.");
        TextView newtime=(TextView) findViewById(R.id.time);

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        DatabaseReference databaseReference=reference.child("quizmaster-89038").child("From");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String from = dataSnapshot.getValue(String.class);
                newtime.setText(from);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        init();
    }

    private void init(){
        mDates.add("From");
        mTests.add("MCQ TEST ");
       // mTimes.add("00.00 to 23.59");
        mButtons.add("Start Test");

       /* mDates.add("TUESDAY");
        mTests.add("MCQ TEST 2");
        mTimes.add("00.00 to 23.59");
        mButtons.add("Start Test");


        mDates.add("WEDNESDAY");
        mTests.add("MCQ TEST 3");
        mTimes.add("00.00 to 23.59");
        mButtons.add("Start Test");


        mDates.add("THURSDAY");
        mTests.add("MCQ TEST 4");
        mTimes.add("00.00 to 23.59");
        mButtons.add("Start Test");


        mDates.add("FRIDAY");
        mTests.add("MCQ TEST 5");
        mTimes.add("00.00 to 23.59");
        mButtons.add("Start Test");


*/
       initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        rmk.virtusa.com.quizmaster.RecyclerViewAdapter adapter= new rmk.virtusa.com.quizmaster.RecyclerViewAdapter(this,mTests,mDates,mButtons);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
