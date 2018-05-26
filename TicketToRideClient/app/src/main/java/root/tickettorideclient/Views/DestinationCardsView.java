package root.tickettorideclient.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Model.InGameModels.City;
import Model.InGameModels.DestinationCard;
import root.tickettorideclient.R;

/**
 * Created by Massiel on 5/25/2018.
 */

public class DestinationCardsView extends Fragment {
    View v;
    RecyclerView cardListRecyclerView;
    DestinationsAdapter destinationsAdapter;
    ArrayList<DestinationCard>userDestinationCards = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_destination_cards, container, false);
        createList();
        return v;
    }

    public void createList(){
        cardListRecyclerView = (RecyclerView) cardListRecyclerView.findViewById(R.id.destinationCardsRecyclerView);
        cardListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    public void updateUI(){
        addFakeDestinations();
        destinationsAdapter = new DestinationsAdapter(userDestinationCards);
    }

    public void addFakeDestinations(){
        for(int i = 0; i < 3; i++){
            City city1 = new City("City" + i, 0.0, 0.0);
            City city2 = new City("City" + (i + 1), 0.0, 0.0);
            DestinationCard destinationCard = new DestinationCard(city1, city2, i, i);
            userDestinationCards.add(destinationCard);
        }
    }

    public class DestinationCardHolder extends RecyclerView.ViewHolder{
        TextView destinationCard;
        TextView completion;

        public DestinationCardHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.game_list_item, parent, false));
            destinationCard = (TextView) itemView.findViewById(R.id.destinationCardItem);
            completion = (TextView) itemView.findViewById(R.id.completion);
        }

        public void bind(final DestinationCard destinationCard){
            String route = destinationCard.getCity1() + " to " + destinationCard.getCity2() + "\n";
            route += "Points: "  + destinationCard.getPointValue();
            String completionMessage;
            if(destinationCard.isComplete())
                completionMessage = "Completed!";

            else
                completionMessage = "Not completed";

            this.destinationCard.setText(route);
            completion.setText(completionMessage);
        }
    }

    public class DestinationsAdapter extends RecyclerView.Adapter<DestinationCardHolder>{
        private ArrayList<DestinationCard>destinationCards;

        public DestinationsAdapter(ArrayList<DestinationCard>destinationCards){
            this.destinationCards = destinationCards;
        }

        @Override
        public DestinationCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new DestinationCardHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(DestinationCardHolder holder, int position) {
            DestinationCard destinationCard = destinationCards.get(position);
            holder.bind(destinationCard);
        }

        @Override
        public int getItemCount() {
            return destinationCards.size();
        }
    }
}
