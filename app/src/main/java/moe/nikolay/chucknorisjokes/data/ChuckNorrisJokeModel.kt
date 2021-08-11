package moe.nikolay.chucknorisjokes.data

data class ChuckNorrisModel(
    val type: String,
    val value: List<ChuckNorrisJokeModel>
)

data class ChuckNorrisJokeModel(
    val id: Long,
    val joke: String,
    val categories: List<String>
)
