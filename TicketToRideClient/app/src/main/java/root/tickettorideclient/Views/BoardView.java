package root.tickettorideclient.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;

import root.tickettorideclient.R;

/**
 * Created by Massiel on 5/21/2018.
 */

public class BoardView extends Fragment implements OnMapReadyCallback{

    GoogleMap myGoogleMap;
    MapView myMapView;
    View myView;

    TextView userPointsBanner;
    TextView userTrainsBanner;

    TextView yourHandBanner;
    TextView yourHandDisplay;

    TextView availableCardsBanner;
    LinearLayout availableCardsDisplay;
    View trainCardsDeck;
    View destinationCardsDeck;
    View faceUpCard1;
    View faceUpCard2;
    View faceUpCard3;
    View faceUpCard4;
    View faceUpCard5;
    View trainCardsLeftInDeck;

    TextView viewYourDestinationCardsBanner;

    TextView chatboxBanner;
    LinearLayout chatBoxContainer;
    TextView chatBox;
    EditText typedMessage;
    Button sendMessageButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_board, container, false);
        setUpTopInputs();
        setUpBottomInputs();
        return myView;
    }

    public void setUpTopInputs(){
        userPointsBanner = (TextView) myView.findViewById(R.id.pointsDisplay);
        userTrainsBanner = (TextView) myView.findViewById(R.id.trainDisplay);

        yourHandDisplay = (TextView)myView.findViewById(R.id.yourHandDisplay);
        yourHandBanner = (TextView)myView.findViewById(R.id.yourHandBanner);
        yourHandBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(yourHandDisplay.getVisibility() == View.VISIBLE)
                    yourHandDisplay.setVisibility(View.GONE);

                else
                    yourHandDisplay.setVisibility(View.VISIBLE);
            }
        });

        availableCardsDisplay = (LinearLayout) myView.findViewById(R.id.availableCardsDisplay);
        availableCardsBanner = (TextView)myView.findViewById(R.id.availableCardsBanner);
        availableCardsBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(availableCardsDisplay.getVisibility() == View.VISIBLE)
                    availableCardsDisplay.setVisibility(View.GONE);
                else
                    availableCardsDisplay.setVisibility(View.VISIBLE);
            }
        });

        trainCardsDeck = (TextView)myView.findViewById(R.id.trainCardsDeck);
        destinationCardsDeck = (TextView)myView.findViewById(R.id.destinationCardsDeck);

        faceUpCard1 = (View) myView.findViewById(R.id.faceUpCard1);
        faceUpCard2 = (View) myView.findViewById(R.id.faceUpCard2);
        faceUpCard3 =  (View) myView.findViewById(R.id.faceUpCard3);
        faceUpCard4 =  (View) myView.findViewById(R.id.faceUpCard4);
        faceUpCard5 = (View) myView.findViewById(R.id.faceUpCard5);
        trainCardsLeftInDeck = (View) myView.findViewById(R.id.trainCardsLeftInDeck);

        viewYourDestinationCardsBanner = (TextView) myView.findViewById(R.id.viewYourDestinationCardsBanner);
    }

    public void setUpBottomInputs(){
        chatBoxContainer = (LinearLayout)myView.findViewById(R.id.chatboxContainer);
        chatBox = (TextView) myView.findViewById(R.id.chatBox);
        typedMessage = (EditText) myView.findViewById(R.id.typeMessageLine);
        sendMessageButton = (Button) myView.findViewById(R.id.readyButton);
        chatboxBanner = (TextView)myView.findViewById(R.id.chatAndHistoryBanner);

        chatboxBanner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(chatBoxContainer.getVisibility() == View.VISIBLE)
                    chatBoxContainer.setVisibility(View.GONE);
                else
                    chatBoxContainer.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myMapView = (MapView) myView.findViewById(R.id.map);

        if(myMapView != null){
            myMapView.onCreate(null);
            myMapView.onResume();
            myMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        myGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = myGoogleMap.getUiSettings();
        uiSettings.setZoomGesturesEnabled(true);
    }
}