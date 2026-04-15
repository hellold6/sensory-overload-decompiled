package androidx.media3.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.RepeatModeUtil;
import androidx.media3.common.util.Util;
import androidx.media3.ui.PlayerControlView;
import androidx.media3.ui.TimeBar;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* loaded from: classes.dex */
public class PlayerControlView extends FrameLayout {
    public static final int DEFAULT_REPEAT_TOGGLE_MODES = 0;
    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    public static final int DEFAULT_TIME_BAR_MIN_UPDATE_INTERVAL_MS = 200;
    private static final int MAX_UPDATE_INTERVAL_MS = 1000;
    public static final int MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100;
    private static final float[] PLAYBACK_SPEEDS;
    private static final int SETTINGS_AUDIO_TRACK_SELECTION_POSITION = 1;
    private static final int SETTINGS_PLAYBACK_SPEED_POSITION = 0;
    private static final String TAG = "PlayerControlView";
    private long[] adGroupTimesMs;
    private final View audioTrackButton;
    private final AudioTrackSelectionAdapter audioTrackSelectionAdapter;
    private final float buttonAlphaDisabled;
    private final float buttonAlphaEnabled;
    private final ComponentListener componentListener;
    private final Class<?> compositionPlayerClazz;
    private final Method compositionPlayerIsScrubbingModeEnabledMethod;
    private final Method compositionPlayerSetScrubbingModeEnabledMethod;
    private final PlayerControlViewLayoutManager controlViewLayoutManager;
    private long currentWindowOffset;
    private final TextView durationView;
    private final Class<?> exoplayerClazz;
    private long[] extraAdGroupTimesMs;
    private boolean[] extraPlayedAdGroups;
    private final View fastForwardButton;
    private final TextView fastForwardButtonTextView;
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    private final ImageView fullscreenButton;
    private final String fullscreenEnterContentDescription;
    private final Drawable fullscreenEnterDrawable;
    private final String fullscreenExitContentDescription;
    private final Drawable fullscreenExitDrawable;
    private boolean isAttachedToWindow;
    private boolean isFullscreen;
    private final Method isScrubbingModeEnabledMethod;
    private final ImageView minimalFullscreenButton;
    private boolean multiWindowTimeBar;
    private boolean needToHideBars;
    private final ImageView nextButton;
    private OnFullScreenModeChangedListener onFullScreenModeChangedListener;
    private final Drawable pauseButtonDrawable;
    private final Timeline.Period period;
    private final Drawable playButtonDrawable;
    private final ImageView playPauseButton;
    private final PlaybackSpeedAdapter playbackSpeedAdapter;
    private final View playbackSpeedButton;
    private boolean[] playedAdGroups;
    private Player player;
    private final TextView positionView;
    private final ImageView previousButton;
    private ProgressUpdateListener progressUpdateListener;
    private final String repeatAllButtonContentDescription;
    private final Drawable repeatAllButtonDrawable;
    private final String repeatOffButtonContentDescription;
    private final Drawable repeatOffButtonDrawable;
    private final String repeatOneButtonContentDescription;
    private final Drawable repeatOneButtonDrawable;
    private final ImageView repeatToggleButton;
    private int repeatToggleModes;
    private final Resources resources;
    private final View rewindButton;
    private final TextView rewindButtonTextView;
    private boolean scrubbing;
    private final Method setScrubbingModeEnabledMethod;
    private final SettingsAdapter settingsAdapter;
    private final View settingsButton;
    private final RecyclerView settingsView;
    private final PopupWindow settingsWindow;
    private final int settingsWindowMargin;
    private boolean showMultiWindowTimeBar;
    private boolean showPlayButtonIfSuppressed;
    private int showTimeoutMs;
    private final ImageView shuffleButton;
    private final Drawable shuffleOffButtonDrawable;
    private final String shuffleOffContentDescription;
    private final Drawable shuffleOnButtonDrawable;
    private final String shuffleOnContentDescription;
    private final ImageView subtitleButton;
    private final Drawable subtitleOffButtonDrawable;
    private final String subtitleOffContentDescription;
    private final Drawable subtitleOnButtonDrawable;
    private final String subtitleOnContentDescription;
    private final TextTrackSelectionAdapter textTrackSelectionAdapter;
    private final TimeBar timeBar;
    private int timeBarMinUpdateIntervalMs;
    private boolean timeBarScrubbingEnabled;
    private final TrackNameProvider trackNameProvider;
    private final Runnable updateProgressAction;
    private final CopyOnWriteArrayList<VisibilityListener> visibilityListeners;
    private final ImageView vrButton;
    private final Timeline.Window window;

    @Deprecated
    /* loaded from: classes.dex */
    public interface OnFullScreenModeChangedListener {
        void onFullScreenModeChanged(boolean z);
    }

    /* loaded from: classes.dex */
    public interface ProgressUpdateListener {
        void onProgressUpdate(long j, long j2);
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface VisibilityListener {
        void onVisibilityChange(int i);
    }

    private static boolean isHandledMediaKey(int i) {
        return i == 90 || i == 89 || i == 85 || i == 79 || i == 126 || i == 127 || i == 87 || i == 88;
    }

    static {
        MediaLibraryInfo.registerModule("media3.ui");
        PLAYBACK_SPEEDS = new float[]{0.25f, 0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 2.0f};
    }

    public PlayerControlView(Context context) {
        this(context, null);
    }

    public PlayerControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PlayerControlView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, attributeSet);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(54:1|(7:3|4|5|6|7|8|9)(1:131)|10|(6:11|12|13|14|15|16)|(6:17|18|19|20|21|22)|(3:23|24|25)|26|27|28|29|30|31|32|33|34|35|36|37|(1:39)|40|(1:42)|43|(1:45)|46|(1:48)|49|(1:51)(1:(1:100)(1:101))|52|(1:54)|55|(1:57)|58|(1:60)|61|(1:63)|64|(1:66)(1:(1:97)(1:98))|67|(1:69)|70|(1:72)(2:92|(1:94)(1:95))|73|(1:75)|76|(1:78)|79|(1:81)|82|(1:84)(1:91)|85|(1:87)|88|89|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(59:1|(7:3|4|5|6|7|8|9)(1:131)|10|11|12|13|14|15|16|(6:17|18|19|20|21|22)|(3:23|24|25)|26|27|28|29|30|31|32|33|34|35|36|37|(1:39)|40|(1:42)|43|(1:45)|46|(1:48)|49|(1:51)(1:(1:100)(1:101))|52|(1:54)|55|(1:57)|58|(1:60)|61|(1:63)|64|(1:66)(1:(1:97)(1:98))|67|(1:69)|70|(1:72)(2:92|(1:94)(1:95))|73|(1:75)|76|(1:78)|79|(1:81)|82|(1:84)(1:91)|85|(1:87)|88|89|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0253, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0252, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0250, code lost:
    
        r2 = null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0377  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x05f7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0302  */
    /* JADX WARN: Type inference failed for: r21v0 */
    /* JADX WARN: Type inference failed for: r21v1 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r3v28, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r42v1 */
    /* JADX WARN: Type inference failed for: r42v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public PlayerControlView(android.content.Context r44, android.util.AttributeSet r45, int r46, android.util.AttributeSet r47) {
        /*
            Method dump skipped, instructions count: 1543
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.ui.PlayerControlView.<init>(android.content.Context, android.util.AttributeSet, int, android.util.AttributeSet):void");
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        boolean z = true;
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        if (player != null && player.getApplicationLooper() != Looper.getMainLooper()) {
            z = false;
        }
        Assertions.checkArgument(z);
        Player player2 = this.player;
        if (player2 == player) {
            return;
        }
        if (player2 != null) {
            player2.removeListener(this.componentListener);
        }
        this.player = player;
        if (player != null) {
            player.addListener(this.componentListener);
        }
        updateAll();
    }

    @Deprecated
    public void setShowMultiWindowTimeBar(boolean z) {
        this.showMultiWindowTimeBar = z;
        updateTimeline();
    }

    public void setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
        this.showPlayButtonIfSuppressed = z;
        updatePlayPauseButton();
    }

    public void setExtraAdGroupMarkers(long[] jArr, boolean[] zArr) {
        if (jArr == null) {
            this.extraAdGroupTimesMs = new long[0];
            this.extraPlayedAdGroups = new boolean[0];
        } else {
            boolean[] zArr2 = (boolean[]) Assertions.checkNotNull(zArr);
            Assertions.checkArgument(jArr.length == zArr2.length);
            this.extraAdGroupTimesMs = jArr;
            this.extraPlayedAdGroups = zArr2;
        }
        updateTimeline();
    }

    @Deprecated
    public void addVisibilityListener(VisibilityListener visibilityListener) {
        Assertions.checkNotNull(visibilityListener);
        this.visibilityListeners.add(visibilityListener);
    }

    @Deprecated
    public void removeVisibilityListener(VisibilityListener visibilityListener) {
        this.visibilityListeners.remove(visibilityListener);
    }

    public void setProgressUpdateListener(ProgressUpdateListener progressUpdateListener) {
        this.progressUpdateListener = progressUpdateListener;
    }

    public void setShowRewindButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.rewindButton, z);
        updateNavigation();
    }

    public void setShowFastForwardButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.fastForwardButton, z);
        updateNavigation();
    }

    public void setShowPreviousButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.previousButton, z);
        updateNavigation();
    }

    public void setShowNextButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.nextButton, z);
        updateNavigation();
    }

    public int getShowTimeoutMs() {
        return this.showTimeoutMs;
    }

    public void setShowTimeoutMs(int i) {
        this.showTimeoutMs = i;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
    }

    public int getRepeatToggleModes() {
        return this.repeatToggleModes;
    }

    public void setRepeatToggleModes(int i) {
        this.repeatToggleModes = i;
        Player player = this.player;
        if (player != null && player.isCommandAvailable(15)) {
            int repeatMode = this.player.getRepeatMode();
            if (i == 0 && repeatMode != 0) {
                this.player.setRepeatMode(0);
            } else if (i == 1 && repeatMode == 2) {
                this.player.setRepeatMode(1);
            } else if (i == 2 && repeatMode == 1) {
                this.player.setRepeatMode(2);
            }
        }
        this.controlViewLayoutManager.setShowButton(this.repeatToggleButton, i != 0);
        updateRepeatModeButton();
    }

    public boolean getShowShuffleButton() {
        return this.controlViewLayoutManager.getShowButton(this.shuffleButton);
    }

    public void setShowShuffleButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.shuffleButton, z);
        updateShuffleButton();
    }

    public boolean getShowSubtitleButton() {
        return this.controlViewLayoutManager.getShowButton(this.subtitleButton);
    }

    public void setShowSubtitleButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.subtitleButton, z);
    }

    public boolean getShowVrButton() {
        return this.controlViewLayoutManager.getShowButton(this.vrButton);
    }

    public void setShowVrButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.vrButton, z);
    }

    public void setVrButtonListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.vrButton;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
            updateButton(onClickListener != null, this.vrButton);
        }
    }

    public void setAnimationEnabled(boolean z) {
        this.controlViewLayoutManager.setAnimationEnabled(z);
    }

    public boolean isAnimationEnabled() {
        return this.controlViewLayoutManager.isAnimationEnabled();
    }

    public void setTimeBarScrubbingEnabled(boolean z) {
        this.timeBarScrubbingEnabled = z;
    }

    public void setTimeBarMinUpdateInterval(int i) {
        this.timeBarMinUpdateIntervalMs = Util.constrainValue(i, 16, 1000);
    }

    @Deprecated
    public void setOnFullScreenModeChangedListener(OnFullScreenModeChangedListener onFullScreenModeChangedListener) {
        this.onFullScreenModeChangedListener = onFullScreenModeChangedListener;
        updateFullscreenButtonVisibility(this.fullscreenButton, onFullScreenModeChangedListener != null);
        updateFullscreenButtonVisibility(this.minimalFullscreenButton, onFullScreenModeChangedListener != null);
    }

    public void show() {
        this.controlViewLayoutManager.show();
    }

    public void hide() {
        this.controlViewLayoutManager.hide();
    }

    public void hideImmediately() {
        this.controlViewLayoutManager.hideImmediately();
    }

    public boolean isFullyVisible() {
        return this.controlViewLayoutManager.isFullyVisible();
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyOnVisibilityChange() {
        Iterator<VisibilityListener> it = this.visibilityListeners.iterator();
        while (it.hasNext()) {
            it.next().onVisibilityChange(getVisibility());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateAll() {
        updatePlayPauseButton();
        updateNavigation();
        updateRepeatModeButton();
        updateShuffleButton();
        updateTrackLists();
        updatePlaybackSpeedList();
        updateTimeline();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlayPauseButton() {
        int i;
        if (isVisible() && this.isAttachedToWindow && this.playPauseButton != null) {
            boolean shouldShowPlayButton = Util.shouldShowPlayButton(this.player, this.showPlayButtonIfSuppressed);
            Drawable drawable = shouldShowPlayButton ? this.playButtonDrawable : this.pauseButtonDrawable;
            if (shouldShowPlayButton) {
                i = R.string.exo_controls_play_description;
            } else {
                i = R.string.exo_controls_pause_description;
            }
            this.playPauseButton.setImageDrawable(drawable);
            this.playPauseButton.setContentDescription(this.resources.getString(i));
            updateButton(Util.shouldEnablePlayPauseButton(this.player), this.playPauseButton);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNavigation() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (isVisible() && this.isAttachedToWindow) {
            Player player = this.player;
            if (player != null) {
                if (this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(player, this.window)) {
                    z = player.isCommandAvailable(10);
                } else {
                    z = player.isCommandAvailable(5);
                }
                z3 = player.isCommandAvailable(7);
                z4 = player.isCommandAvailable(11);
                z5 = player.isCommandAvailable(12);
                z2 = player.isCommandAvailable(9);
            } else {
                z = false;
                z2 = false;
                z3 = false;
                z4 = false;
                z5 = false;
            }
            if (z4) {
                updateRewindButton();
            }
            if (z5) {
                updateFastForwardButton();
            }
            updateButton(z3, this.previousButton);
            updateButton(z4, this.rewindButton);
            updateButton(z5, this.fastForwardButton);
            updateButton(z2, this.nextButton);
            TimeBar timeBar = this.timeBar;
            if (timeBar != null) {
                timeBar.setEnabled(z);
            }
        }
    }

    private void updateRewindButton() {
        Player player = this.player;
        int seekBackIncrement = (int) ((player != null ? player.getSeekBackIncrement() : 5000L) / 1000);
        TextView textView = this.rewindButtonTextView;
        if (textView != null) {
            textView.setText(String.valueOf(seekBackIncrement));
        }
        View view = this.rewindButton;
        if (view != null) {
            view.setContentDescription(this.resources.getQuantityString(R.plurals.exo_controls_rewind_by_amount_description, seekBackIncrement, Integer.valueOf(seekBackIncrement)));
        }
    }

    private void updateFastForwardButton() {
        Player player = this.player;
        int seekForwardIncrement = (int) ((player != null ? player.getSeekForwardIncrement() : C.DEFAULT_SEEK_FORWARD_INCREMENT_MS) / 1000);
        TextView textView = this.fastForwardButtonTextView;
        if (textView != null) {
            textView.setText(String.valueOf(seekForwardIncrement));
        }
        View view = this.fastForwardButton;
        if (view != null) {
            view.setContentDescription(this.resources.getQuantityString(R.plurals.exo_controls_fastforward_by_amount_description, seekForwardIncrement, Integer.valueOf(seekForwardIncrement)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRepeatModeButton() {
        ImageView imageView;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.repeatToggleButton) != null) {
            if (this.repeatToggleModes == 0) {
                updateButton(false, imageView);
                return;
            }
            Player player = this.player;
            if (player == null || !player.isCommandAvailable(15)) {
                updateButton(false, this.repeatToggleButton);
                this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
                return;
            }
            updateButton(true, this.repeatToggleButton);
            int repeatMode = player.getRepeatMode();
            if (repeatMode == 0) {
                this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
            } else if (repeatMode == 1) {
                this.repeatToggleButton.setImageDrawable(this.repeatOneButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOneButtonContentDescription);
            } else {
                if (repeatMode != 2) {
                    return;
                }
                this.repeatToggleButton.setImageDrawable(this.repeatAllButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatAllButtonContentDescription);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShuffleButton() {
        ImageView imageView;
        String str;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.shuffleButton) != null) {
            Player player = this.player;
            if (!this.controlViewLayoutManager.getShowButton(imageView)) {
                updateButton(false, this.shuffleButton);
                return;
            }
            if (player == null || !player.isCommandAvailable(14)) {
                updateButton(false, this.shuffleButton);
                this.shuffleButton.setImageDrawable(this.shuffleOffButtonDrawable);
                this.shuffleButton.setContentDescription(this.shuffleOffContentDescription);
                return;
            }
            updateButton(true, this.shuffleButton);
            this.shuffleButton.setImageDrawable(player.getShuffleModeEnabled() ? this.shuffleOnButtonDrawable : this.shuffleOffButtonDrawable);
            ImageView imageView2 = this.shuffleButton;
            if (player.getShuffleModeEnabled()) {
                str = this.shuffleOnContentDescription;
            } else {
                str = this.shuffleOffContentDescription;
            }
            imageView2.setContentDescription(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTrackLists() {
        initTrackSelectionAdapter();
        updateButton(this.textTrackSelectionAdapter.getItemCount() > 0, this.subtitleButton);
        updateSettingsButton();
    }

    private void initTrackSelectionAdapter() {
        this.textTrackSelectionAdapter.clear();
        this.audioTrackSelectionAdapter.clear();
        Player player = this.player;
        if (player != null && player.isCommandAvailable(30) && this.player.isCommandAvailable(29)) {
            Tracks currentTracks = this.player.getCurrentTracks();
            this.audioTrackSelectionAdapter.init(gatherSupportedTrackInfosOfType(currentTracks, 1));
            if (this.controlViewLayoutManager.getShowButton(this.subtitleButton)) {
                this.textTrackSelectionAdapter.init(gatherSupportedTrackInfosOfType(currentTracks, 3));
            } else {
                this.textTrackSelectionAdapter.init(ImmutableList.of());
            }
        }
    }

    private ImmutableList<TrackInformation> gatherSupportedTrackInfosOfType(Tracks tracks, int i) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        ImmutableList<Tracks.Group> groups = tracks.getGroups();
        for (int i2 = 0; i2 < groups.size(); i2++) {
            Tracks.Group group = groups.get(i2);
            if (group.getType() == i) {
                for (int i3 = 0; i3 < group.length; i3++) {
                    if (group.isTrackSupported(i3)) {
                        Format trackFormat = group.getTrackFormat(i3);
                        if ((trackFormat.selectionFlags & 2) == 0) {
                            builder.add((ImmutableList.Builder) new TrackInformation(tracks, i2, i3, this.trackNameProvider.getTrackName(trackFormat)));
                        }
                    }
                }
            }
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTimeline() {
        Timeline timeline;
        int i;
        long j;
        long j2;
        Player player = this.player;
        if (player == null) {
            return;
        }
        boolean z = true;
        this.multiWindowTimeBar = this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(player, this.window);
        long j3 = 0;
        this.currentWindowOffset = 0L;
        if (player.isCommandAvailable(17)) {
            timeline = player.getCurrentTimeline();
        } else {
            timeline = Timeline.EMPTY;
        }
        boolean isEmpty = timeline.isEmpty();
        long j4 = C.TIME_UNSET;
        if (!isEmpty) {
            int currentMediaItemIndex = player.getCurrentMediaItemIndex();
            boolean z2 = this.multiWindowTimeBar;
            int i2 = z2 ? 0 : currentMediaItemIndex;
            int windowCount = z2 ? timeline.getWindowCount() - 1 : currentMediaItemIndex;
            i = 0;
            long j5 = 0;
            while (true) {
                if (i2 > windowCount) {
                    break;
                }
                if (i2 == currentMediaItemIndex) {
                    this.currentWindowOffset = Util.usToMs(j5);
                }
                timeline.getWindow(i2, this.window);
                if (this.window.durationUs == j4) {
                    Assertions.checkState(this.multiWindowTimeBar ^ z);
                    break;
                }
                for (int i3 = this.window.firstPeriodIndex; i3 <= this.window.lastPeriodIndex; i3++) {
                    timeline.getPeriod(i3, this.period);
                    int removedAdGroupCount = this.period.getRemovedAdGroupCount();
                    int adGroupCount = this.period.getAdGroupCount();
                    while (removedAdGroupCount < adGroupCount) {
                        long j6 = j3;
                        long adGroupTimeUs = this.period.getAdGroupTimeUs(removedAdGroupCount);
                        if (adGroupTimeUs == Long.MIN_VALUE) {
                            if (this.period.durationUs == j4) {
                                j2 = j4;
                                removedAdGroupCount++;
                                j3 = j6;
                                j4 = j2;
                            } else {
                                adGroupTimeUs = this.period.durationUs;
                            }
                        }
                        j2 = j4;
                        long positionInWindowUs = adGroupTimeUs + this.period.getPositionInWindowUs();
                        if (positionInWindowUs >= j6) {
                            long[] jArr = this.adGroupTimesMs;
                            if (i == jArr.length) {
                                int length = jArr.length == 0 ? 1 : jArr.length * 2;
                                this.adGroupTimesMs = Arrays.copyOf(jArr, length);
                                this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, length);
                            }
                            this.adGroupTimesMs[i] = Util.usToMs(positionInWindowUs + j5);
                            this.playedAdGroups[i] = this.period.hasPlayedAdGroup(removedAdGroupCount);
                            i++;
                        }
                        removedAdGroupCount++;
                        j3 = j6;
                        j4 = j2;
                    }
                }
                j5 += this.window.durationUs;
                i2++;
                j3 = j3;
                z = true;
            }
            j = j5;
        } else {
            if (player.isCommandAvailable(16)) {
                long contentDuration = player.getContentDuration();
                if (contentDuration != C.TIME_UNSET) {
                    j = Util.msToUs(contentDuration);
                    i = 0;
                }
            }
            i = 0;
            j = 0;
        }
        long usToMs = Util.usToMs(j);
        TextView textView = this.durationView;
        if (textView != null) {
            textView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, usToMs));
        }
        TimeBar timeBar = this.timeBar;
        if (timeBar != null) {
            timeBar.setDuration(usToMs);
            int length2 = this.extraAdGroupTimesMs.length;
            int i4 = i + length2;
            long[] jArr2 = this.adGroupTimesMs;
            if (i4 > jArr2.length) {
                this.adGroupTimesMs = Arrays.copyOf(jArr2, i4);
                this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, i4);
            }
            System.arraycopy(this.extraAdGroupTimesMs, 0, this.adGroupTimesMs, i, length2);
            System.arraycopy(this.extraPlayedAdGroups, 0, this.playedAdGroups, i, length2);
            this.timeBar.setAdGroupTimesMs(this.adGroupTimesMs, this.playedAdGroups, i4);
        }
        updateProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgress() {
        long j;
        long j2;
        if (isVisible() && this.isAttachedToWindow) {
            Player player = this.player;
            if (player == null || !player.isCommandAvailable(16)) {
                j = 0;
                j2 = 0;
            } else {
                j = this.currentWindowOffset + player.getContentPosition();
                j2 = this.currentWindowOffset + player.getContentBufferedPosition();
            }
            TextView textView = this.positionView;
            if (textView != null && !this.scrubbing) {
                textView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, j));
            }
            TimeBar timeBar = this.timeBar;
            if (timeBar != null) {
                timeBar.setPosition(j);
                this.timeBar.setBufferedPosition(isScrubbingModeEnabled(player) ? j : j2);
            }
            ProgressUpdateListener progressUpdateListener = this.progressUpdateListener;
            if (progressUpdateListener != null) {
                progressUpdateListener.onProgressUpdate(j, j2);
            }
            removeCallbacks(this.updateProgressAction);
            int playbackState = player == null ? 1 : player.getPlaybackState();
            if (player == null || !player.isPlaying()) {
                if (playbackState == 4 || playbackState == 1) {
                    return;
                }
                postDelayed(this.updateProgressAction, 1000L);
                return;
            }
            TimeBar timeBar2 = this.timeBar;
            long min = Math.min(timeBar2 != null ? timeBar2.getPreferredUpdateDelay() : 1000L, 1000 - (j % 1000));
            postDelayed(this.updateProgressAction, Util.constrainValue(player.getPlaybackParameters().speed > 0.0f ? ((float) min) / r0 : 1000L, this.timeBarMinUpdateIntervalMs, 1000L));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlaybackSpeedList() {
        Player player = this.player;
        if (player == null) {
            return;
        }
        this.playbackSpeedAdapter.updateSelectedIndex(player.getPlaybackParameters().speed);
        this.settingsAdapter.setSubTextAtPosition(0, this.playbackSpeedAdapter.getSelectedText());
        updateSettingsButton();
    }

    private void updateSettingsButton() {
        updateButton(this.settingsAdapter.hasSettingsToShow(), this.settingsButton);
    }

    private void updateSettingsWindowSize() {
        this.settingsView.measure(0, 0);
        this.settingsWindow.setWidth(Math.min(this.settingsView.getMeasuredWidth(), getWidth() - (this.settingsWindowMargin * 2)));
        this.settingsWindow.setHeight(Math.min(getHeight() - (this.settingsWindowMargin * 2), this.settingsView.getMeasuredHeight()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displaySettingsWindow(RecyclerView.Adapter<?> adapter, View view) {
        this.settingsView.setAdapter(adapter);
        updateSettingsWindowSize();
        this.needToHideBars = false;
        this.settingsWindow.dismiss();
        this.needToHideBars = true;
        this.settingsWindow.showAsDropDown(view, (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin, (-this.settingsWindow.getHeight()) - this.settingsWindowMargin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlaybackSpeed(float f) {
        Player player = this.player;
        if (player == null || !player.isCommandAvailable(13)) {
            return;
        }
        Player player2 = this.player;
        player2.setPlaybackParameters(player2.getPlaybackParameters().withSpeed(f));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestPlayPauseFocus() {
        ImageView imageView = this.playPauseButton;
        if (imageView != null) {
            imageView.requestFocus();
        }
    }

    private void updateButton(boolean z, View view) {
        if (view == null) {
            return;
        }
        view.setEnabled(z);
        view.setAlpha(z ? this.buttonAlphaEnabled : this.buttonAlphaDisabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void seekToTimeBarPosition(Player player, long j) {
        if (this.multiWindowTimeBar) {
            if (player.isCommandAvailable(17) && player.isCommandAvailable(10)) {
                Timeline currentTimeline = player.getCurrentTimeline();
                int windowCount = currentTimeline.getWindowCount();
                int i = 0;
                while (true) {
                    long durationMs = currentTimeline.getWindow(i, this.window).getDurationMs();
                    if (j < durationMs) {
                        break;
                    }
                    if (i == windowCount - 1) {
                        j = durationMs;
                        break;
                    } else {
                        j -= durationMs;
                        i++;
                    }
                }
                player.seekTo(i, j);
            }
        } else if (player.isCommandAvailable(5)) {
            player.seekTo(j);
        }
        updateProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFullscreenButtonClicked(View view) {
        updateIsFullscreen(!this.isFullscreen);
    }

    public void updateIsFullscreen(boolean z) {
        if (this.isFullscreen == z) {
            return;
        }
        this.isFullscreen = z;
        updateFullscreenButtonForState(this.fullscreenButton, z);
        updateFullscreenButtonForState(this.minimalFullscreenButton, z);
        OnFullScreenModeChangedListener onFullScreenModeChangedListener = this.onFullScreenModeChangedListener;
        if (onFullScreenModeChangedListener != null) {
            onFullScreenModeChangedListener.onFullScreenModeChanged(z);
        }
    }

    private void updateFullscreenButtonForState(ImageView imageView, boolean z) {
        if (imageView == null) {
            return;
        }
        if (z) {
            imageView.setImageDrawable(this.fullscreenExitDrawable);
            imageView.setContentDescription(this.fullscreenExitContentDescription);
        } else {
            imageView.setImageDrawable(this.fullscreenEnterDrawable);
            imageView.setContentDescription(this.fullscreenEnterContentDescription);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSettingViewClicked(int i) {
        if (i == 0) {
            displaySettingsWindow(this.playbackSpeedAdapter, (View) Assertions.checkNotNull(this.settingsButton));
        } else if (i == 1) {
            displaySettingsWindow(this.audioTrackSelectionAdapter, (View) Assertions.checkNotNull(this.settingsButton));
        } else {
            this.settingsWindow.dismiss();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.controlViewLayoutManager.onAttachedToWindow();
        this.isAttachedToWindow = true;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
        updateAll();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.controlViewLayoutManager.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        removeCallbacks(this.updateProgressAction);
        this.controlViewLayoutManager.removeHideCallbacks();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        Player player = this.player;
        if (player == null || !isHandledMediaKey(keyCode)) {
            return false;
        }
        if (keyEvent.getAction() != 0) {
            return true;
        }
        if (keyCode == 90) {
            if (player.getPlaybackState() == 4 || !player.isCommandAvailable(12)) {
                return true;
            }
            player.seekForward();
            return true;
        }
        if (keyCode == 89 && player.isCommandAvailable(11)) {
            player.seekBack();
            return true;
        }
        if (keyEvent.getRepeatCount() != 0) {
            return true;
        }
        if (keyCode == 79 || keyCode == 85) {
            Util.handlePlayPauseButtonAction(player, this.showPlayButtonIfSuppressed);
            return true;
        }
        if (keyCode == 87) {
            if (!player.isCommandAvailable(9)) {
                return true;
            }
            player.seekToNext();
            return true;
        }
        if (keyCode == 88) {
            if (!player.isCommandAvailable(7)) {
                return true;
            }
            player.seekToPrevious();
            return true;
        }
        if (keyCode == 126) {
            Util.handlePlayButtonAction(player);
            return true;
        }
        if (keyCode != 127) {
            return true;
        }
        Util.handlePauseButtonAction(player);
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.controlViewLayoutManager.onLayout(z, i, i2, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i4 - i2;
        int i10 = i8 - i6;
        if (!(i3 - i == i7 - i5 && i9 == i10) && this.settingsWindow.isShowing()) {
            updateSettingsWindowSize();
            this.settingsWindow.update(view, (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin, (-this.settingsWindow.getHeight()) - this.settingsWindowMargin, -1, -1);
        }
    }

    private static boolean canShowMultiWindowTimeBar(Player player, Timeline.Window window) {
        Timeline currentTimeline;
        int windowCount;
        if (!player.isCommandAvailable(17) || (windowCount = (currentTimeline = player.getCurrentTimeline()).getWindowCount()) <= 1 || windowCount > 100) {
            return false;
        }
        for (int i = 0; i < windowCount; i++) {
            if (currentTimeline.getWindow(i, window).durationUs == C.TIME_UNSET) {
                return false;
            }
        }
        return true;
    }

    private static void initializeFullscreenButton(View view, View.OnClickListener onClickListener) {
        if (view == null) {
            return;
        }
        view.setVisibility(8);
        view.setOnClickListener(onClickListener);
    }

    private static void updateFullscreenButtonVisibility(View view, boolean z) {
        if (view == null) {
            return;
        }
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    private static int getRepeatToggleModes(TypedArray typedArray, int i) {
        return typedArray.getInt(R.styleable.PlayerControlView_repeat_toggle_modes, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    public boolean isScrubbingModeEnabled(Player player) {
        try {
            if (isExoPlayer(player) && ((Boolean) Assertions.checkNotNull(((Method) Assertions.checkNotNull(this.isScrubbingModeEnabledMethod)).invoke(player, new Object[0]))).booleanValue()) {
                return true;
            }
            if (isCompositionPlayer(player)) {
                if (((Boolean) Assertions.checkNotNull(((Method) Assertions.checkNotNull(this.compositionPlayerIsScrubbingModeEnabledMethod)).invoke(player, new Object[0]))).booleanValue()) {
                    return true;
                }
            }
            return false;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    public boolean isExoPlayer(Player player) {
        Class<?> cls;
        return (player == null || (cls = this.exoplayerClazz) == null || !cls.isAssignableFrom(player.getClass())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    public boolean isCompositionPlayer(Player player) {
        Class<?> cls;
        return (player == null || (cls = this.compositionPlayerClazz) == null || !cls.isAssignableFrom(player.getClass())) ? false : true;
    }

    /* loaded from: classes.dex */
    private final class ComponentListener implements Player.Listener, TimeBar.OnScrubListener, View.OnClickListener, PopupWindow.OnDismissListener {
        private ComponentListener() {
        }

        @Override // androidx.media3.common.Player.Listener
        public void onEvents(Player player, Player.Events events) {
            if (events.containsAny(4, 5, 13)) {
                PlayerControlView.this.updatePlayPauseButton();
            }
            if (events.containsAny(4, 5, 7, 13)) {
                PlayerControlView.this.updateProgress();
            }
            if (events.containsAny(8, 13)) {
                PlayerControlView.this.updateRepeatModeButton();
            }
            if (events.containsAny(9, 13)) {
                PlayerControlView.this.updateShuffleButton();
            }
            if (events.containsAny(8, 9, 11, 0, 16, 17, 13)) {
                PlayerControlView.this.updateNavigation();
            }
            if (events.containsAny(11, 0, 13)) {
                PlayerControlView.this.updateTimeline();
            }
            if (events.containsAny(12, 13)) {
                PlayerControlView.this.updatePlaybackSpeedList();
            }
            if (events.containsAny(2, 13)) {
                PlayerControlView.this.updateTrackLists();
            }
        }

        @Override // androidx.media3.ui.TimeBar.OnScrubListener
        public void onScrubStart(TimeBar timeBar, long j) {
            PlayerControlView.this.scrubbing = true;
            if (PlayerControlView.this.positionView != null) {
                PlayerControlView.this.positionView.setText(Util.getStringForTime(PlayerControlView.this.formatBuilder, PlayerControlView.this.formatter, j));
            }
            PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
            if (PlayerControlView.this.player == null || !PlayerControlView.this.timeBarScrubbingEnabled) {
                return;
            }
            PlayerControlView playerControlView = PlayerControlView.this;
            if (playerControlView.isExoPlayer(playerControlView.player)) {
                try {
                    ((Method) Assertions.checkNotNull(PlayerControlView.this.setScrubbingModeEnabledMethod)).invoke(PlayerControlView.this.player, true);
                    return;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            PlayerControlView playerControlView2 = PlayerControlView.this;
            if (playerControlView2.isCompositionPlayer(playerControlView2.player)) {
                try {
                    ((Method) Assertions.checkNotNull(PlayerControlView.this.compositionPlayerSetScrubbingModeEnabledMethod)).invoke(PlayerControlView.this.player, true);
                    return;
                } catch (IllegalAccessException | InvocationTargetException e2) {
                    throw new RuntimeException(e2);
                }
            }
            Log.w(PlayerControlView.TAG, "Time bar scrubbing is enabled, but player is not an ExoPlayer or CompositionPlayer instance, so ignoring (because we can't enable scrubbing mode). player.class=" + ((Player) Assertions.checkNotNull(PlayerControlView.this.player)).getClass());
        }

        @Override // androidx.media3.ui.TimeBar.OnScrubListener
        public void onScrubMove(TimeBar timeBar, long j) {
            if (PlayerControlView.this.positionView != null) {
                PlayerControlView.this.positionView.setText(Util.getStringForTime(PlayerControlView.this.formatBuilder, PlayerControlView.this.formatter, j));
            }
            PlayerControlView playerControlView = PlayerControlView.this;
            if (playerControlView.isScrubbingModeEnabled(playerControlView.player)) {
                PlayerControlView playerControlView2 = PlayerControlView.this;
                playerControlView2.seekToTimeBarPosition(playerControlView2.player, j);
            }
        }

        @Override // androidx.media3.ui.TimeBar.OnScrubListener
        public void onScrubStop(TimeBar timeBar, long j, boolean z) {
            PlayerControlView.this.scrubbing = false;
            if (PlayerControlView.this.player != null) {
                if (!z) {
                    PlayerControlView playerControlView = PlayerControlView.this;
                    playerControlView.seekToTimeBarPosition(playerControlView.player, j);
                }
                PlayerControlView playerControlView2 = PlayerControlView.this;
                if (playerControlView2.isExoPlayer(playerControlView2.player)) {
                    try {
                        ((Method) Assertions.checkNotNull(PlayerControlView.this.setScrubbingModeEnabledMethod)).invoke(PlayerControlView.this.player, false);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    PlayerControlView playerControlView3 = PlayerControlView.this;
                    if (playerControlView3.isCompositionPlayer(playerControlView3.player)) {
                        try {
                            ((Method) Assertions.checkNotNull(PlayerControlView.this.compositionPlayerSetScrubbingModeEnabledMethod)).invoke(PlayerControlView.this.player, false);
                        } catch (IllegalAccessException | InvocationTargetException e2) {
                            throw new RuntimeException(e2);
                        }
                    }
                }
            }
            PlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            if (PlayerControlView.this.needToHideBars) {
                PlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Player player = PlayerControlView.this.player;
            if (player == null) {
                return;
            }
            PlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
            if (PlayerControlView.this.nextButton != view) {
                if (PlayerControlView.this.previousButton != view) {
                    if (PlayerControlView.this.fastForwardButton != view) {
                        if (PlayerControlView.this.rewindButton != view) {
                            if (PlayerControlView.this.playPauseButton == view) {
                                Util.handlePlayPauseButtonAction(player, PlayerControlView.this.showPlayButtonIfSuppressed);
                                return;
                            }
                            if (PlayerControlView.this.repeatToggleButton != view) {
                                if (PlayerControlView.this.shuffleButton != view) {
                                    if (PlayerControlView.this.settingsButton == view) {
                                        PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                                        PlayerControlView playerControlView = PlayerControlView.this;
                                        playerControlView.displaySettingsWindow(playerControlView.settingsAdapter, PlayerControlView.this.settingsButton);
                                        return;
                                    }
                                    if (PlayerControlView.this.playbackSpeedButton == view) {
                                        PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                                        PlayerControlView playerControlView2 = PlayerControlView.this;
                                        playerControlView2.displaySettingsWindow(playerControlView2.playbackSpeedAdapter, PlayerControlView.this.playbackSpeedButton);
                                        return;
                                    } else if (PlayerControlView.this.audioTrackButton == view) {
                                        PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                                        PlayerControlView playerControlView3 = PlayerControlView.this;
                                        playerControlView3.displaySettingsWindow(playerControlView3.audioTrackSelectionAdapter, PlayerControlView.this.audioTrackButton);
                                        return;
                                    } else {
                                        if (PlayerControlView.this.subtitleButton == view) {
                                            PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                                            PlayerControlView playerControlView4 = PlayerControlView.this;
                                            playerControlView4.displaySettingsWindow(playerControlView4.textTrackSelectionAdapter, PlayerControlView.this.subtitleButton);
                                            return;
                                        }
                                        return;
                                    }
                                }
                                if (player.isCommandAvailable(14)) {
                                    player.setShuffleModeEnabled(!player.getShuffleModeEnabled());
                                    return;
                                }
                                return;
                            }
                            if (player.isCommandAvailable(15)) {
                                player.setRepeatMode(RepeatModeUtil.getNextRepeatMode(player.getRepeatMode(), PlayerControlView.this.repeatToggleModes));
                                return;
                            }
                            return;
                        }
                        if (player.isCommandAvailable(11)) {
                            player.seekBack();
                            return;
                        }
                        return;
                    }
                    if (player.getPlaybackState() == 4 || !player.isCommandAvailable(12)) {
                        return;
                    }
                    player.seekForward();
                    return;
                }
                if (player.isCommandAvailable(7)) {
                    player.seekToPrevious();
                    return;
                }
                return;
            }
            if (player.isCommandAvailable(9)) {
                player.seekToNext();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SettingsAdapter extends RecyclerView.Adapter<SettingViewHolder> {
        private final Drawable[] iconIds;
        private final String[] mainTexts;
        private final String[] subTexts;

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int i) {
            return i;
        }

        public SettingsAdapter(String[] strArr, Drawable[] drawableArr) {
            this.mainTexts = strArr;
            this.subTexts = new String[strArr.length];
            this.iconIds = drawableArr;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public SettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SettingViewHolder(LayoutInflater.from(PlayerControlView.this.getContext()).inflate(R.layout.exo_styled_settings_list_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SettingViewHolder settingViewHolder, int i) {
            if (shouldShowSetting(i)) {
                settingViewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                settingViewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
            settingViewHolder.mainTextView.setText(this.mainTexts[i]);
            if (this.subTexts[i] == null) {
                settingViewHolder.subTextView.setVisibility(8);
            } else {
                settingViewHolder.subTextView.setText(this.subTexts[i]);
            }
            if (this.iconIds[i] == null) {
                settingViewHolder.iconView.setVisibility(8);
            } else {
                settingViewHolder.iconView.setImageDrawable(this.iconIds[i]);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mainTexts.length;
        }

        public void setSubTextAtPosition(int i, String str) {
            this.subTexts[i] = str;
        }

        public boolean hasSettingsToShow() {
            return shouldShowSetting(1) || shouldShowSetting(0);
        }

        private boolean shouldShowSetting(int i) {
            if (PlayerControlView.this.player == null) {
                return false;
            }
            if (i == 0) {
                return PlayerControlView.this.player.isCommandAvailable(13);
            }
            if (i != 1) {
                return true;
            }
            return PlayerControlView.this.player.isCommandAvailable(30) && PlayerControlView.this.player.isCommandAvailable(29);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SettingViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iconView;
        private final TextView mainTextView;
        private final TextView subTextView;

        public SettingViewHolder(View view) {
            super(view);
            if (Build.VERSION.SDK_INT < 26) {
                view.setFocusable(true);
            }
            this.mainTextView = (TextView) view.findViewById(R.id.exo_main_text);
            this.subTextView = (TextView) view.findViewById(R.id.exo_sub_text);
            this.iconView = (ImageView) view.findViewById(R.id.exo_icon);
            view.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$SettingViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    PlayerControlView.SettingViewHolder.this.m526x7eeeb754(view2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$androidx-media3-ui-PlayerControlView$SettingViewHolder, reason: not valid java name */
        public /* synthetic */ void m526x7eeeb754(View view) {
            PlayerControlView.this.onSettingViewClicked(getBindingAdapterPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class PlaybackSpeedAdapter extends RecyclerView.Adapter<SubSettingViewHolder> {
        private final String[] playbackSpeedTexts;
        private final float[] playbackSpeeds;
        private int selectedIndex;

        public PlaybackSpeedAdapter(String[] strArr, float[] fArr) {
            this.playbackSpeedTexts = strArr;
            this.playbackSpeeds = fArr;
        }

        public void updateSelectedIndex(float f) {
            int i = 0;
            float f2 = Float.MAX_VALUE;
            int i2 = 0;
            while (true) {
                float[] fArr = this.playbackSpeeds;
                if (i < fArr.length) {
                    float abs = Math.abs(f - fArr[i]);
                    if (abs < f2) {
                        i2 = i;
                        f2 = abs;
                    }
                    i++;
                } else {
                    this.selectedIndex = i2;
                    return;
                }
            }
        }

        public String getSelectedText() {
            return this.playbackSpeedTexts[this.selectedIndex];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public SubSettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SubSettingViewHolder(LayoutInflater.from(PlayerControlView.this.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, final int i) {
            if (i < this.playbackSpeedTexts.length) {
                subSettingViewHolder.textView.setText(this.playbackSpeedTexts[i]);
            }
            if (i == this.selectedIndex) {
                subSettingViewHolder.itemView.setSelected(true);
                subSettingViewHolder.checkView.setVisibility(0);
            } else {
                subSettingViewHolder.itemView.setSelected(false);
                subSettingViewHolder.checkView.setVisibility(4);
            }
            subSettingViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$PlaybackSpeedAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PlayerControlView.PlaybackSpeedAdapter.this.m525x9de2ddb7(i, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onBindViewHolder$0$androidx-media3-ui-PlayerControlView$PlaybackSpeedAdapter, reason: not valid java name */
        public /* synthetic */ void m525x9de2ddb7(int i, View view) {
            if (i != this.selectedIndex) {
                PlayerControlView.this.setPlaybackSpeed(this.playbackSpeeds[i]);
            }
            PlayerControlView.this.settingsWindow.dismiss();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.playbackSpeedTexts.length;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class TrackInformation {
        public final Tracks.Group trackGroup;
        public final int trackIndex;
        public final String trackName;

        public TrackInformation(Tracks tracks, int i, int i2, String str) {
            this.trackGroup = tracks.getGroups().get(i);
            this.trackIndex = i2;
            this.trackName = str;
        }

        public boolean isSelected() {
            return this.trackGroup.isTrackSelected(this.trackIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class TextTrackSelectionAdapter extends TrackSelectionAdapter {
        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void onTrackSelection(String str) {
        }

        private TextTrackSelectionAdapter() {
            super();
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void init(List<TrackInformation> list) {
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    break;
                }
                if (list.get(i).isSelected()) {
                    z = true;
                    break;
                }
                i++;
            }
            if (PlayerControlView.this.subtitleButton != null) {
                ImageView imageView = PlayerControlView.this.subtitleButton;
                PlayerControlView playerControlView = PlayerControlView.this;
                imageView.setImageDrawable(z ? playerControlView.subtitleOnButtonDrawable : playerControlView.subtitleOffButtonDrawable);
                PlayerControlView.this.subtitleButton.setContentDescription(z ? PlayerControlView.this.subtitleOnContentDescription : PlayerControlView.this.subtitleOffContentDescription);
            }
            this.tracks = list;
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder) {
            boolean z;
            subSettingViewHolder.textView.setText(R.string.exo_track_selection_none);
            int i = 0;
            while (true) {
                if (i >= this.tracks.size()) {
                    z = true;
                    break;
                } else {
                    if (this.tracks.get(i).isSelected()) {
                        z = false;
                        break;
                    }
                    i++;
                }
            }
            subSettingViewHolder.checkView.setVisibility(z ? 0 : 4);
            subSettingViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$TextTrackSelectionAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PlayerControlView.TextTrackSelectionAdapter.this.m527x7bd5d809(view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onBindViewHolderAtZeroPosition$0$androidx-media3-ui-PlayerControlView$TextTrackSelectionAdapter, reason: not valid java name */
        public /* synthetic */ void m527x7bd5d809(View view) {
            if (PlayerControlView.this.player == null || !PlayerControlView.this.player.isCommandAvailable(29)) {
                return;
            }
            PlayerControlView.this.player.setTrackSelectionParameters(PlayerControlView.this.player.getTrackSelectionParameters().buildUpon().clearOverridesOfType(3).setIgnoredTextSelectionFlags(-3).setPreferredTextLanguage(null).setPreferredTextRoleFlags(0).build());
            PlayerControlView.this.settingsWindow.dismiss();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            super.onBindViewHolder(subSettingViewHolder, i);
            if (i > 0) {
                subSettingViewHolder.checkView.setVisibility(this.tracks.get(i + (-1)).isSelected() ? 0 : 4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class AudioTrackSelectionAdapter extends TrackSelectionAdapter {
        private AudioTrackSelectionAdapter() {
            super();
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder) {
            subSettingViewHolder.textView.setText(R.string.exo_track_selection_auto);
            subSettingViewHolder.checkView.setVisibility(hasSelectionOverride(((Player) Assertions.checkNotNull(PlayerControlView.this.player)).getTrackSelectionParameters()) ? 4 : 0);
            subSettingViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$AudioTrackSelectionAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PlayerControlView.AudioTrackSelectionAdapter.this.m524xa84b12b0(view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onBindViewHolderAtZeroPosition$0$androidx-media3-ui-PlayerControlView$AudioTrackSelectionAdapter, reason: not valid java name */
        public /* synthetic */ void m524xa84b12b0(View view) {
            if (PlayerControlView.this.player == null || !PlayerControlView.this.player.isCommandAvailable(29)) {
                return;
            }
            ((Player) Util.castNonNull(PlayerControlView.this.player)).setTrackSelectionParameters(PlayerControlView.this.player.getTrackSelectionParameters().buildUpon().clearOverridesOfType(1).setTrackTypeDisabled(1, false).build());
            PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, PlayerControlView.this.getResources().getString(R.string.exo_track_selection_auto));
            PlayerControlView.this.settingsWindow.dismiss();
        }

        private boolean hasSelectionOverride(TrackSelectionParameters trackSelectionParameters) {
            for (int i = 0; i < this.tracks.size(); i++) {
                if (trackSelectionParameters.overrides.containsKey(this.tracks.get(i).trackGroup.getMediaTrackGroup())) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void onTrackSelection(String str) {
            PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, str);
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void init(List<TrackInformation> list) {
            this.tracks = list;
            TrackSelectionParameters trackSelectionParameters = ((Player) Assertions.checkNotNull(PlayerControlView.this.player)).getTrackSelectionParameters();
            if (list.isEmpty()) {
                PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, PlayerControlView.this.getResources().getString(R.string.exo_track_selection_none));
                return;
            }
            if (!hasSelectionOverride(trackSelectionParameters)) {
                PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, PlayerControlView.this.getResources().getString(R.string.exo_track_selection_auto));
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                TrackInformation trackInformation = list.get(i);
                if (trackInformation.isSelected()) {
                    PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, trackInformation.trackName);
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public abstract class TrackSelectionAdapter extends RecyclerView.Adapter<SubSettingViewHolder> {
        protected List<TrackInformation> tracks = new ArrayList();

        public abstract void init(List<TrackInformation> list);

        protected abstract void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder);

        protected abstract void onTrackSelection(String str);

        protected TrackSelectionAdapter() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public SubSettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SubSettingViewHolder(LayoutInflater.from(PlayerControlView.this.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, viewGroup, false));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            final Player player = PlayerControlView.this.player;
            if (player == null) {
                return;
            }
            if (i == 0) {
                onBindViewHolderAtZeroPosition(subSettingViewHolder);
                return;
            }
            final TrackInformation trackInformation = this.tracks.get(i - 1);
            final TrackGroup mediaTrackGroup = trackInformation.trackGroup.getMediaTrackGroup();
            boolean z = player.getTrackSelectionParameters().overrides.get(mediaTrackGroup) != null && trackInformation.isSelected();
            subSettingViewHolder.textView.setText(trackInformation.trackName);
            subSettingViewHolder.checkView.setVisibility(z ? 0 : 4);
            subSettingViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$TrackSelectionAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PlayerControlView.TrackSelectionAdapter.this.m528x45c3fb1a(player, mediaTrackGroup, trackInformation, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onBindViewHolder$0$androidx-media3-ui-PlayerControlView$TrackSelectionAdapter, reason: not valid java name */
        public /* synthetic */ void m528x45c3fb1a(Player player, TrackGroup trackGroup, TrackInformation trackInformation, View view) {
            if (player.isCommandAvailable(29)) {
                player.setTrackSelectionParameters(player.getTrackSelectionParameters().buildUpon().setOverrideForType(new TrackSelectionOverride(trackGroup, ImmutableList.of(Integer.valueOf(trackInformation.trackIndex)))).setTrackTypeDisabled(trackInformation.trackGroup.getType(), false).build());
                onTrackSelection(trackInformation.trackName);
                PlayerControlView.this.settingsWindow.dismiss();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.tracks.isEmpty()) {
                return 0;
            }
            return this.tracks.size() + 1;
        }

        protected void clear() {
            this.tracks = Collections.emptyList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SubSettingViewHolder extends RecyclerView.ViewHolder {
        public final View checkView;
        public final TextView textView;

        public SubSettingViewHolder(View view) {
            super(view);
            if (Build.VERSION.SDK_INT < 26) {
                view.setFocusable(true);
            }
            this.textView = (TextView) view.findViewById(R.id.exo_text);
            this.checkView = view.findViewById(R.id.exo_check);
        }
    }
}
