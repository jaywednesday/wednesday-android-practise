package com.wednesdaykotlinpractise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wednesdaykotlinpractise.R
import com.wednesdaykotlinpractise.db.DBHelper
import com.wednesdaykotlinpractise.models.Bank
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.item_bank.view.*

class FavBankListAdapter(private val context: Context, private val banks: ArrayList<Bank>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val dbHelper = DBHelper(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_bank, parent, false)
        return  BankViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BankViewHolder).bindBank(banks.get(position))
    }

    override fun getItemCount(): Int {
        return banks.size
    }

    inner class BankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindBank(bank:Bank) {

            itemView.tvBankName.text = "Bank Name: ${bank.bank}"
            itemView.tvIFSC.text = "IFSC Code: ${bank.ifsc}"
            itemView.tvBranch.text = "Branch: ${bank.branch}"
            itemView.tvAddress.text = "Address: ${bank.address}"
            itemView.tvContact.text = "Contact: ${bank.contact}"
            itemView.tvState.text = "State: ${bank.state}"
            itemView.tvDistrict.text = "State: ${bank.district}"
            itemView.tvRTGS.text = "RTGS: ${bank.rtgs}"
            itemView.tvCity.text = "City: ${bank.city}"
            itemView.btnRemoveBankFromFavList.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            when (view) {
                itemView.btnRemoveBankFromFavList -> {
                    dbHelper.deleteBank(banks.get(adapterPosition).id!!)
                    banks.removeAt(adapterPosition)
                    notifyDataSetChanged()
                }
            }
        }
    }
}