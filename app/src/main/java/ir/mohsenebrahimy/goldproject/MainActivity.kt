package ir.mohsenebrahimy.goldproject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.mohsenebrahimy.goldproject.databinding.ActivityMainBinding
import ir.mohsenebrahimy.goldproject.remote.model.LabelAndPrice
import ir.mohsenebrahimy.goldproject.remote.repository.TimeApiRepository
import ir.mohsenebrahimy.goldproject.remote.request.TimeRequest
import ir.mohsenebrahimy.goldproject.remote.model.TimeModel
import ir.mohsenebrahimy.goldproject.remote.repository.PriceApiRepository
import ir.mohsenebrahimy.goldproject.remote.request.PriceRequest
import ir.mohsenebrahimy.goldproject.remote.model.PriceModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var goldPrice = ArrayList<LabelAndPrice>()
    private var cryptoPrice = ArrayList<LabelAndPrice>()
    private var currencyPrice = ArrayList<LabelAndPrice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get time api and set on time box
        TimeApiRepository.instance.getTime(
            object : TimeRequest {
                override fun onSuccess(data: TimeModel) {
                    val date = data.date
                    val text = "${date.dayName} ${date.day} ${date.monthName} ${date.year}"
                    binding.txtTime.text = text
                }

                override fun onNotSuccess(message: String) {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: String) {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                }
            }
        )

        // get prices with api and set on each recycler view
        getPrice()

        binding.rcyPriceList.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        // change color after click
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            binding.txtGold.setOnClickListener {
                binding.txtGold.setTextColor(getColor(R.color.gold_text))
                binding.txtCrypto.setTextColor(getColor(R.color.gray_text))
                binding.txtCurrency.setTextColor(getColor(R.color.gray_text))

                setData(goldPrice)
            }

            binding.txtCrypto.setOnClickListener {
                binding.txtGold.setTextColor(getColor(R.color.gray_text))
                binding.txtCrypto.setTextColor(getColor(R.color.gold_text))
                binding.txtCurrency.setTextColor(getColor(R.color.gray_text))

                setData(cryptoPrice)
            }

            binding.txtCurrency.setOnClickListener {
                binding.txtGold.setTextColor(getColor(R.color.gray_text))
                binding.txtCrypto.setTextColor(getColor(R.color.gray_text))
                binding.txtCurrency.setTextColor(getColor(R.color.gold_text))

                setData(currencyPrice)
            }
        } else {
            @Suppress("DEPRECATION")
            binding.txtGold.setOnClickListener {
                binding.txtGold.setTextColor(resources.getColor(R.color.gold_text))
                binding.txtCrypto.setTextColor(resources.getColor(R.color.gray_text))
                binding.txtCurrency.setTextColor(resources.getColor(R.color.gray_text))

                setData(goldPrice)
            }

            @Suppress("DEPRECATION")
            binding.txtCrypto.setOnClickListener {
                binding.txtGold.setTextColor(resources.getColor(R.color.gray_text))
                binding.txtCrypto.setTextColor(resources.getColor(R.color.gold_text))
                binding.txtCurrency.setTextColor(resources.getColor(R.color.gray_text))

                setData(cryptoPrice)
            }

            @Suppress("DEPRECATION")
            binding.txtCurrency.setOnClickListener {
                binding.txtGold.setTextColor(resources.getColor(R.color.gray_text))
                binding.txtCrypto.setTextColor(resources.getColor(R.color.gray_text))
                binding.txtCurrency.setTextColor(resources.getColor(R.color.gold_text))

                setData(currencyPrice)
            }
        }
    }

    // create function for get price data for once
    private fun getPrice() {
        PriceApiRepository.instance.getPrice(
            object : PriceRequest {
                override fun onSuccess(data: PriceModel) {
                    goldPrice.addAll(data.data.golds)
                    cryptoPrice.addAll(data.data.cryptocurrencies)
                    currencyPrice.addAll(data.data.currencies)


                    setData(goldPrice)
                }

                override fun onNotSuccess(message: String) {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: String) {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    private fun setData(data: ArrayList<LabelAndPrice>) {
        binding.rcyPriceList.adapter = RecyclerMainAdapter(data)
    }
}