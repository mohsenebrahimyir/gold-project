package ir.mohsenebrahimy.goldproject.remote.model

data class PriceModel(
    val last_update: String,
    val message: String,
    val source: String,
    val success: Int,
    val data: Data
)

data class Data(
    val cryptocurrencies: List<LabelAndPrice>,
    val currencies: List<LabelAndPrice>,
    val golds: List<LabelAndPrice>
)

data class LabelAndPrice(
    val label: String,
    val price: Int
)