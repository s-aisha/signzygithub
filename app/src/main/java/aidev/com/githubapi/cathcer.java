package aidev.com.githubapi;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class cathcer extends RecyclerView.Adapter<cathcer.ViewHolder>  {


    private List<Initialiser> listitem;
    Context ctx;


    public cathcer(Context ctx, List<Initialiser> listitem) {
        this.listitem = listitem;
        this.ctx = ctx;
    }

    @Override
    public cathcer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Initialiser homeInitialiser = listitem.get(position);


        String link = homeInitialiser.getLink();

        holder.links.setText(""+link);

        holder.links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = ""+homeInitialiser.getHtmlUrl()+"/"+homeInitialiser.getLink();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setPackage("com.android.chrome");
                try {
                    ctx.startActivity(i);
                } catch (ActivityNotFoundException e) {

                    i.setPackage(null);
                    ctx.startActivity(i);
                }
            }
        });

        if (homeInitialiser.getDescription().equals("null")) {
            holder.desc.setText("Not available");
        } else {
            holder.desc.setText(homeInitialiser.getDescription());
        }


    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView links;
        public TextView desc;


        public ViewHolder(View itemView) {
            super(itemView);

            links = (TextView) itemView.findViewById(R.id.reponame);
            desc = (TextView) itemView.findViewById(R.id.description);


        }
    }

    public void updateList(List<Initialiser> listitem){
        this.listitem = listitem;
        notifyDataSetChanged();
    }
}
