package com.example.toshiba.moneyapps2.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.toshiba.moneyapps2.Model.RRincian;
import com.example.toshiba.moneyapps2.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class RincianAdapter extends RecyclerView.Adapter<RincianAdapter.RincianViewHolder>
{
    private Context mCtx3;
    private List<RRincian> RincianList;

    public RincianAdapter(Context mCtx3, List<RRincian> RincianList)
    {
        this.mCtx3 = mCtx3;
        this.RincianList = RincianList;
    }

    class RincianViewHolder extends RecyclerView.ViewHolder
    {
        TextView judulproses, jumlahproses;
        CardView layout1;

        public RincianViewHolder(View itemView)
        {
            super(itemView);
            judulproses = itemView.findViewById(R.id.JudulProses);
            jumlahproses = itemView.findViewById(R.id.JumlahProses);;
            layout1 = itemView.findViewById(R.id.layout1);
        }
    }

    @Override
    public RincianViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx3);
        View view = inflater.inflate(R.layout.rvv_rincian,null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new RincianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RincianViewHolder holder, int position)
    {
        //konversi nilai
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        final RRincian rRincian = RincianList.get(position);

        holder.judulproses.setText(rRincian.getJudulrincian());


        //if keliling menetap
        if (rRincian.getStatusrincian().equals("Pengeluaran"))
        {
            holder.jumlahproses.setText("- " + formatRupiah.format(rRincian.getUang()));
            holder.jumlahproses.setTextColor(Color.parseColor("#dd0d0d"));
        }

        else
        {
            holder.jumlahproses.setText("+ " + formatRupiah.format(rRincian.getUang()));
            holder.jumlahproses.setTextColor(Color.parseColor("#0cb30c"));
        }


//        holder.layout1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(mCtx3, DetailRincian.class);
//                intent.putExtra("idprod", String.valueOf(rRincian.getIdprod()));
//                intent.putExtra("NamaProduk", rRincian.getJudul());
//                intent.putExtra("HargaProduk", String.valueOf(rRincian.getHarga()));
//                intent.putExtra("StatusProduk", rRincian.getStatus());
//                intent.putExtra("Gambar",rRincian.getFoto());
//                intent.putExtra("jarakproduk",rRincian.getJarak());
//                mCtx3.startActivity(intent) ;
//            }
//        });
    }

    @Override
    public int getItemCount(){ return RincianList.size();}
}

