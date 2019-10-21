package com.dleal.news_data.remote

import com.dleal.data.datasources.remote.BaseRemoteDataSource
import io.reactivex.Single
import java.net.URL
import kotlin.math.roundToInt

/**
 * Created by Daniel Leal on 2019-10-20.
 */
class NewsRemoteDataSource : BaseRemoteDataSource() {

    fun fetchNewsElements(page: Int, pageSize: Int): Single<List<NewsElementDto>> {
        val randomNumberOfArticles = (0 until pageSize).shuffled().first()
        val articleList = (0 until randomNumberOfArticles).map {
            createArticle(it)
        }
        val videoList = (randomNumberOfArticles until pageSize).map {
            createVideo(it)
        }
        return Single.just(articleList.union(videoList).shuffled())
    }

    private fun createArticle(id: Int): ArticleDto =
        ArticleDto(
            id = id.toLong(),
            imageUrl = randomImageUrl(id),
            headline = randomHeadline(),
            description = randomDescription(),
            url = randomUrl(id),
            tags = randomTags(),
            isPremium = randomBoolean()
        )

    private fun createVideo(id: Int): VideoDto =
        VideoDto(
            id = id.toLong(),
            imageUrl = randomImageUrl(id),
            headline = randomHeadline(),
            type = randomVideoType(),
            duration = randomVideoLength(),
            url = randomUrl(id),
            isPremium = randomBoolean()
        )

    private fun randomImageUrl(id: Int) = IMAGE_URL.format(id)

    private fun randomHeadline(): String {
        val loremIpsum = URL(LOREM_IPSUM_URL.format(1, ParagraphLength.SHORT.code)).readText()
        return loremIpsum.toCharArray().toList().shuffled().joinToString()
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
        return loremIpsum.toCharArray().toList().shuffled().joinToString()
    }

    private fun randomUrl(id: Int) = ARTICLE_URL.format(id)

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

private const val IMAGE_URL = "https://picsum.photos/id/%d/480/720"
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