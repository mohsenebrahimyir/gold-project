package ir.mohsenebrahimy.goldproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ir.mohsenebrahimy.goldproject.databinding.RecyclerMainItemBinding
import ir.mohsenebrahimy.goldproject.remote.model.LabelAndPrice

class RecyclerMainAdapter (
    private val data: ArrayList<LabelAndPrice>
): RecyclerView.Adapter<RecyclerMainAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RecyclerMainItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.setData(data[position])
    }

    inner class MainViewHolder(
        private val binding: RecyclerMainItemBinding
    ): ViewHolder(binding.root) {

        fun setData(data : LabelAndPrice) {

            binding.txtLabel.text = data.label
            binding.txtPrice.text = (data.price / 10).formatDecimalSeparator()
            when (data.label) {
                "طلای 18 عیار" -> binding.imgIcon.setImageResource(R.drawable.ic_18)
                "طلای 24 عیار" -> binding.imgIcon.setImageResource(R.drawable.ic_24)
                "سکه بهار آزادی" -> binding.imgIcon.setImageResource(R.drawable.ic_coin)
                "نیم سکه بهار آزادی" -> binding.imgIcon.setImageResource(R.drawable.ic_coin)
                "ربع سکه بهار آزادی" -> binding.imgIcon.setImageResource(R.drawable.ic_coin)
                "درهم" -> binding.imgIcon.setImageResource(R.drawable.ic_derham)
                "دلار" -> binding.imgIcon.setImageResource(R.drawable.ic_dolar)
                "پوند" -> binding.imgIcon.setImageResource(R.drawable.ic_pond)
                "یورو" -> binding.imgIcon.setImageResource(R.drawable.ic_uro)
                "بیت کوین" -> binding.imgIcon.setImageResource(R.drawable.ic_btc)
                "تتر" -> binding.imgIcon.setImageResource(R.drawable.ic_usdt)
                else -> binding.imgIcon.setImageResource(R.drawable.ic_gold)
            }
        }

    }
    fun Int.formatDecimalSeparator(): String {
    return toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
    }
}
