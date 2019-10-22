package com.dleal.news_data.remote

import com.dleal.data.datasources.remote.BaseRemoteDataSource
import io.reactivex.Single
import io.reactivex.SingleEmitter
import java.net.URL
import kotlin.math.roundToInt

/**
 * Created by Daniel Leal on 2019-10-20.
 */
class NewsRemoteDataSource : BaseRemoteDataSource() {

    fun fetchNewsElements(page: Int, pageSize: Int): Single<List<NewsElementDto>> {
        val firstIndex = page * pageSize
        val lastIndex = firstIndex + pageSize
        val pageIds = (firstIndex until lastIndex).shuffled()
        val randomNumberOfArticles = (0 until pageSize).shuffled().first()

        return Single.create { emitter: SingleEmitter<List<NewsElementDto>> ->
            val articleList = pageIds.subList(0, randomNumberOfArticles).map {
                createArticle(it + 1L)
            }
            val videoList = (pageIds.subList(randomNumberOfArticles, pageIds.size)).map {
                createVideo(it + 1L)
            }

            val result: List<NewsElementDto> =
                articleList.union(videoList).toList().sortedBy { it.id }

            emitter.onSuccess(
                result
            )
        }
    }

    private fun createArticle(id: Long): ArticleDto =
        ArticleDto(
            id = id,
            imageUrl = randomImageUrl(id),
            headline = randomHeadline(),
            description = randomDescription(),
            url = randomUrl(id),
            tags = randomTags(),
            isPremium = randomBoolean()
        )

    private fun createVideo(id: Long): VideoDto =
        VideoDto(
            id = id,
            imageUrl = randomImageUrl(id),
            headline = randomHeadline(),
            type = randomVideoType(),
            duration = randomVideoLength(),
            url = randomUrl(id),
            isPremium = randomBoolean()
        )

    private fun randomImageUrl(id: Long) = IMAGE_URL.format((Math.random() * 1000 + id).toInt())

    private fun randomHeadline(): String {
        val loremIpsum = URL(LOREM_IPSUM_URL.format(1, ParagraphLength.SHORT.code)).readText()
        return loremIpsum.shuffleWords().joinToString()
    }

    private fun randomDescription(): String {
        val randomParagraphLengthIndex = (0..2).shuffled().first()
        val randomNumberOfParagraphs = (0..5).shuffled().first()
        val loremIpsum = URL(
            LOREM_IPSUM_URL.format(
                randomNumberOfParagraphs,
                ParagraphLength.values()[randomParagraphLengthIndex]
            )
        ).readText()
        return loremIpsum.shuffleWords().joinToString()
    }

    private fun randomUrl(id: Long) = ARTICLE_URL.format(id)

    private fun randomTags(): List<String> {
        val randomTagsLength = (0..2).shuffled().first()
        return TAGS.shuffled().take(randomTagsLength)
    }

    private fun randomBoolean() = Math.random() > 0.5

    private fun randomVideoLength() = (Math.random() * VIDEO_MAX_LENGTH).roundToInt()

    private fun randomVideoType() = TAGS.shuffled().first()
}

private const val LOREM_IPSUM_URL = "https://loripsum.net/api/%d/%s/plaintext"

private enum class ParagraphLength(val code: String) {
    SHORT("short"),
    MEDIUM("medium"),
    LONG("long")
}

private const val IMAGE_URL = "https://picsum.photos/id/%d/720/480"
private const val ARTICLE_URL = "https://myarticles/%d"

private val TAGS = listOf(
    "Lorem",
    "ipsum",
    "dolor",
    "sit",
    "amet",
    "consectetur",
    "adipiscing",
    "elit",
    "Sit",
    "enim",
    "idem",
    "caecus",
    "debilis",
    "Sed",
    "plane",
    "dicit",
    "quod",
    "intellegit"
)

private const val VIDEO_MAX_LENGTH = 3600

private const val REGEX_WORD = """\w+"""

private fun String.shuffleWords() =
    REGEX_WORD.toRegex()
        .findAll(this)
        .map { it.value }
        .toList()
        .shuffled()