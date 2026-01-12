package net.voidarkana.marvelous_menagerie.data.manager.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IndexTagsCodec {

    private String index;

    public IndexTagsCodec(String item) {
        this.index = item;
    }

    public static final Codec<IndexTagsCodec> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("index_name").forGetter(w -> w.index))
                    .apply(instance, IndexTagsCodec::new));

    public static <T> Map<T, List<IndexTagsCodec>> convertToMap(Map<T, List<IndexTagsCodec>> map) {
        return map.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                        .map(indexTagsCodec -> new IndexTagsCodec(
                                indexTagsCodec.getIndex()
                        ))
                        .collect(Collectors.toList())
        ));
    }

    public static <T> Map<T, List<IndexTagsCodec>> convertFromMap(Map<T, List<IndexTagsCodec>> map) {
        return map.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                        .map(indexTagsCodec -> new IndexTagsCodec(
                                indexTagsCodec.getIndex()
                        ))
                        .collect(Collectors.toList())
        ));
    }
    public String getIndex() {
        return index;
    }

}
