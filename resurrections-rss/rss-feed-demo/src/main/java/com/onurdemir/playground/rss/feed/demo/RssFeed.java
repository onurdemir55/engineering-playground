package com.onurdemir.playground.rss.feed.demo;

import io.github.onurdemir55.resurrections.rss.feed.Channel;
import io.github.onurdemir55.resurrections.rss.feed.Item;
import io.github.onurdemir55.resurrections.rss.feed.Rss;
import io.github.onurdemir55.resurrections.rss.feed.element.Category;
import io.github.onurdemir55.resurrections.rss.feed.element.Guid;
import io.github.onurdemir55.resurrections.rss.feed.holder.CDATAValue;
import io.github.onurdemir55.resurrections.rss.feed.holder.PlainValue;
import io.github.onurdemir55.resurrections.rss.io.RssOutput;
import io.github.onurdemir55.resurrections.rss.util.DateParser;

import java.time.Instant;

public class RssFeed {

    private static final Instant PUBLISHED = Instant.parse("2026-02-19T08:30:00Z");

    /**
     * Assembled rather than written inline only because the real line is longer than the style
     * limit for source. The value is exactly what the writer produces.
     */
    private static final String ITEM_DESCRIPTION =
            "<description><![CDATA[<p>Full-year profit rose <b>18%</b> and the board raised the "
                    + "dividend to <b>$1.24</b>. Read the "
                    + "<a href=\"/2026/02/onur-demir-holding-dividend\">full report</a>.</p>]]></description>";


    public static void main(String[] args) {
        String published = DateParser.formatRfc822(PUBLISHED);

        Item item = Item.builder()
                .title(new PlainValue("Onur Demir Holding beats forecasts & lifts its dividend"))
                .link(new PlainValue("https://example.com/2026/02/onur-demir-holding-dividend"))
                .description(new CDATAValue(
                        "<p>Full-year profit rose <b>18%</b> and the board raised the dividend "
                                + "to <b>$1.24</b>. Read the "
                                + "<a href=\"/2026/02/onur-demir-holding-dividend\">full report</a>.</p>"))
                .categories(Category.of("Earnings"), Category.of("Equities"))
                .guid(Guid.of("https://example.com/2026/02/onur-demir-holding-dividend", true))
                .pubDate(new PlainValue(published))
                .build();

        Channel channel = Channel.builder()
                .title(new PlainValue("Markets & Mornings"))
                .link(new PlainValue("https://example.com/"))
                .description(new CDATAValue(
                        "<p>Good news from the markets, before your <i>first coffee</i>.</p>"))
                .language(new PlainValue("en-us"))
                .pubDate(new PlainValue(published))
                .items(item)
                .build();

        Rss rss = Rss.builder().channel(channel).build();

        String feed = RssOutput.outputString(rss).strip();
        System.out.printf("%s%n", feed);
    }

}
