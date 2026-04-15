package androidx.media3.exoplayer.hls;

import android.net.Uri;
import android.util.JsonReader;
import android.util.JsonToken;
import androidx.media3.common.C;
import androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes.dex */
final class AssetListParser implements ParsingLoadable.Parser<HlsInterstitialsAdsLoader.AssetList> {
    private static final String ASSET_LIST_JSON_NAME_ASSET_ARRAY = "ASSETS";
    private static final String ASSET_LIST_JSON_NAME_DURATION = "DURATION";
    private static final String ASSET_LIST_JSON_NAME_URI = "URI";

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
    public HlsInterstitialsAdsLoader.AssetList parse(Uri uri, InputStream inputStream) throws IOException {
        JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));
        try {
            if (jsonReader.peek() != JsonToken.BEGIN_OBJECT) {
                HlsInterstitialsAdsLoader.AssetList assetList = HlsInterstitialsAdsLoader.AssetList.EMPTY;
                jsonReader.close();
                return assetList;
            }
            ImmutableList.Builder builder = new ImmutableList.Builder();
            ImmutableList.Builder builder2 = new ImmutableList.Builder();
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (jsonReader.peek().equals(JsonToken.NAME)) {
                    String nextName = jsonReader.nextName();
                    if (nextName.equals(ASSET_LIST_JSON_NAME_ASSET_ARRAY) && jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
                        parseAssetArray(jsonReader, builder);
                    } else if (jsonReader.peek() == JsonToken.STRING) {
                        builder2.add((ImmutableList.Builder) new HlsInterstitialsAdsLoader.StringAttribute(nextName, jsonReader.nextString()));
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            HlsInterstitialsAdsLoader.AssetList assetList2 = new HlsInterstitialsAdsLoader.AssetList(builder.build(), builder2.build());
            jsonReader.close();
            return assetList2;
        } catch (Throwable th) {
            try {
                jsonReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static void parseAssetArray(JsonReader jsonReader, ImmutableList.Builder<HlsInterstitialsAdsLoader.Asset> builder) throws IOException {
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            if (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                parseAssetObject(jsonReader, builder);
            }
        }
        jsonReader.endArray();
    }

    private static void parseAssetObject(JsonReader jsonReader, ImmutableList.Builder<HlsInterstitialsAdsLoader.Asset> builder) throws IOException {
        jsonReader.beginObject();
        String str = null;
        long j = -9223372036854775807L;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(ASSET_LIST_JSON_NAME_URI) && jsonReader.peek() == JsonToken.STRING) {
                str = jsonReader.nextString();
            } else if (nextName.equals(ASSET_LIST_JSON_NAME_DURATION) && jsonReader.peek() == JsonToken.NUMBER) {
                j = (long) (jsonReader.nextDouble() * 1000000.0d);
            } else {
                jsonReader.skipValue();
            }
        }
        if (str != null && j != C.TIME_UNSET) {
            builder.add((ImmutableList.Builder<HlsInterstitialsAdsLoader.Asset>) new HlsInterstitialsAdsLoader.Asset(Uri.parse(str), j));
        }
        jsonReader.endObject();
    }
}
