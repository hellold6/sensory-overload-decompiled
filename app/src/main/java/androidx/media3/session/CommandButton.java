package androidx.media3.session;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.facebook.common.util.UriUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.ImmutableIntArray;
import com.google.errorprone.annotations.CheckReturnValue;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public final class CommandButton {
    public static final int ICON_ALBUM = 57369;
    public static final int ICON_ARTIST = 57370;
    public static final int ICON_BLOCK = 57675;
    public static final int ICON_BOOKMARK_FILLED = 1042534;
    public static final int ICON_BOOKMARK_UNFILLED = 59494;
    public static final int ICON_CHECK_CIRCLE_FILLED = 1042540;
    public static final int ICON_CHECK_CIRCLE_UNFILLED = 59500;
    public static final int ICON_CLOSED_CAPTIONS = 57372;
    public static final int ICON_CLOSED_CAPTIONS_OFF = 61916;
    public static final int ICON_FAST_FORWARD = 57375;
    public static final int ICON_FEED = 57573;
    public static final int ICON_FLAG_FILLED = 1040723;
    public static final int ICON_FLAG_UNFILLED = 57683;
    public static final int ICON_HEART_FILLED = 1042557;
    public static final int ICON_HEART_UNFILLED = 59517;
    public static final int ICON_MINUS = 57691;
    public static final int ICON_MINUS_CIRCLE_FILLED = 1040712;
    public static final int ICON_MINUS_CIRCLE_UNFILLED = 1040713;
    public static final int ICON_NEXT = 57412;
    public static final int ICON_PAUSE = 57396;
    public static final int ICON_PLAY = 57399;
    public static final int ICON_PLAYBACK_SPEED = 57448;
    public static final int ICON_PLAYBACK_SPEED_0_5 = 62690;
    public static final int ICON_PLAYBACK_SPEED_0_8 = 1045730;
    public static final int ICON_PLAYBACK_SPEED_1_0 = 61389;
    public static final int ICON_PLAYBACK_SPEED_1_2 = 62689;
    public static final int ICON_PLAYBACK_SPEED_1_5 = 62688;
    public static final int ICON_PLAYBACK_SPEED_1_8 = 1045728;
    public static final int ICON_PLAYBACK_SPEED_2_0 = 62699;
    public static final int ICON_PLAYLIST_ADD = 57403;
    public static final int ICON_PLAYLIST_REMOVE = 60288;
    public static final int ICON_PLUS = 57669;
    public static final int ICON_PLUS_CIRCLE_FILLED = 1040711;
    public static final int ICON_PLUS_CIRCLE_UNFILLED = 57671;
    public static final int ICON_PREVIOUS = 57413;
    public static final int ICON_QUALITY = 58409;
    public static final int ICON_QUEUE_ADD = 57436;
    public static final int ICON_QUEUE_NEXT = 57446;
    public static final int ICON_QUEUE_REMOVE = 57447;
    public static final int ICON_RADIO = 58654;
    public static final int ICON_REPEAT_ALL = 57408;
    public static final int ICON_REPEAT_OFF = 1040448;
    public static final int ICON_REPEAT_ONE = 57409;
    public static final int ICON_REWIND = 57376;
    public static final int ICON_SETTINGS = 59576;
    public static final int ICON_SHARE = 59405;
    public static final int ICON_SHUFFLE_OFF = 1040452;
    public static final int ICON_SHUFFLE_ON = 57411;
    public static final int ICON_SHUFFLE_STAR = 1040451;
    public static final int ICON_SIGNAL = 61512;
    public static final int ICON_SKIP_BACK = 57410;
    public static final int ICON_SKIP_BACK_10 = 57433;
    public static final int ICON_SKIP_BACK_15 = 1040473;
    public static final int ICON_SKIP_BACK_30 = 57434;
    public static final int ICON_SKIP_BACK_5 = 57435;
    public static final int ICON_SKIP_FORWARD = 63220;
    public static final int ICON_SKIP_FORWARD_10 = 57430;
    public static final int ICON_SKIP_FORWARD_15 = 1040470;
    public static final int ICON_SKIP_FORWARD_30 = 57431;
    public static final int ICON_SKIP_FORWARD_5 = 57432;
    public static final int ICON_STAR_FILLED = 1042488;
    public static final int ICON_STAR_UNFILLED = 59448;
    public static final int ICON_STOP = 57415;
    public static final int ICON_SUBTITLES = 57416;
    public static final int ICON_SUBTITLES_OFF = 61298;
    public static final int ICON_SYNC = 58919;
    public static final int ICON_THUMB_DOWN_FILLED = 1042651;
    public static final int ICON_THUMB_DOWN_UNFILLED = 59611;
    public static final int ICON_THUMB_UP_FILLED = 1042652;
    public static final int ICON_THUMB_UP_UNFILLED = 59612;
    public static final int ICON_UNDEFINED = 0;
    public static final int ICON_VOLUME_DOWN = 57421;
    public static final int ICON_VOLUME_OFF = 57423;
    public static final int ICON_VOLUME_UP = 57424;
    public static final int SLOT_BACK = 2;
    public static final int SLOT_BACK_SECONDARY = 4;
    public static final int SLOT_CENTRAL = 1;
    public static final int SLOT_FORWARD = 3;
    public static final int SLOT_FORWARD_SECONDARY = 5;
    public static final int SLOT_OVERFLOW = 6;
    public final CharSequence displayName;
    public final Bundle extras;
    public final int icon;
    public final int iconResId;
    public final Uri iconUri;
    public final boolean isEnabled;
    public final int playerCommand;
    public final SessionCommand sessionCommand;
    public final ImmutableIntArray slots;
    private static final String FIELD_SESSION_COMMAND = Util.intToStringMaxRadix(0);
    private static final String FIELD_PLAYER_COMMAND = Util.intToStringMaxRadix(1);
    private static final String FIELD_ICON_RES_ID = Util.intToStringMaxRadix(2);
    private static final String FIELD_DISPLAY_NAME = Util.intToStringMaxRadix(3);
    private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(4);
    private static final String FIELD_ENABLED = Util.intToStringMaxRadix(5);
    private static final String FIELD_ICON_URI = Util.intToStringMaxRadix(6);
    private static final String FIELD_ICON = Util.intToStringMaxRadix(7);
    private static final String FIELD_SLOTS = Util.intToStringMaxRadix(8);

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Icon {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Slot {
    }

    public static int getDefaultSlot(int i, int i2) {
        if (i == 1 || i2 == 57399 || i2 == 57396) {
            return 1;
        }
        if (i == 11 || i == 7 || i == 6 || i2 == 57413 || i2 == 57376 || i2 == 57410 || i2 == 57435 || i2 == 57433 || i2 == 1040473 || i2 == 57434) {
            return 2;
        }
        return (i == 12 || i == 9 || i == 8 || i2 == 57412 || i2 == 57375 || i2 == 63220 || i2 == 57432 || i2 == 57430 || i2 == 1040470 || i2 == 57431) ? 3 : 6;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private CharSequence displayName;
        private boolean enabled;
        private Bundle extras;
        private final int icon;
        private int iconResId;
        private Uri iconUri;
        private int playerCommand;
        private SessionCommand sessionCommand;
        private ImmutableIntArray slots;

        @Deprecated
        public Builder() {
            this(0);
        }

        public Builder(int i) {
            this(i, CommandButton.getIconResIdForIconConstant(i));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(int i, int i2) {
            this.icon = i;
            this.iconResId = i2;
            this.displayName = "";
            this.extras = Bundle.EMPTY;
            this.playerCommand = -1;
            this.enabled = true;
        }

        public Builder setSessionCommand(SessionCommand sessionCommand) {
            Assertions.checkNotNull(sessionCommand, "sessionCommand should not be null.");
            Assertions.checkArgument(this.playerCommand == -1, "playerCommands is already set. Only one of sessionCommand and playerCommand should be set.");
            this.sessionCommand = sessionCommand;
            return this;
        }

        public Builder setPlayerCommand(int i) {
            Assertions.checkArgument(this.sessionCommand == null, "sessionCommand is already set. Only one of sessionCommand and playerCommand should be set.");
            this.playerCommand = i;
            return this;
        }

        @Deprecated
        public Builder setIconResId(int i) {
            return setCustomIconResId(i);
        }

        public Builder setCustomIconResId(int i) {
            this.iconResId = i;
            return this;
        }

        public Builder setIconUri(Uri uri) {
            Assertions.checkArgument(Objects.equals(uri.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME) || Objects.equals(uri.getScheme(), UriUtil.QUALIFIED_RESOURCE_SCHEME), "Only content or resource Uris are supported for CommandButton");
            this.iconUri = uri;
            return this;
        }

        public Builder setDisplayName(CharSequence charSequence) {
            this.displayName = charSequence;
            return this;
        }

        public Builder setEnabled(boolean z) {
            this.enabled = z;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.extras = new Bundle(bundle);
            return this;
        }

        public Builder setSlots(int... iArr) {
            Assertions.checkArgument(iArr.length != 0);
            this.slots = ImmutableIntArray.copyOf(iArr);
            return this;
        }

        public CommandButton build() {
            Assertions.checkState((this.sessionCommand == null) != (this.playerCommand == -1), "Exactly one of sessionCommand and playerCommand should be set");
            if (this.slots == null) {
                this.slots = ImmutableIntArray.of(CommandButton.getDefaultSlot(this.playerCommand, this.icon));
            }
            return new CommandButton(this.sessionCommand, this.playerCommand, this.icon, this.iconResId, this.iconUri, this.displayName, this.extras, this.enabled, this.slots);
        }
    }

    /* loaded from: classes.dex */
    public static final class DisplayConstraints {
        private final SparseArray<Player.Commands> allowedPlayerCommandsPerSlot;
        private final SparseArray<SessionCommands> allowedSessionCommandsPerSlot;
        private final SparseBooleanArray areCustomCommandsAllowedPerSlot;
        private final SparseIntArray maxButtonsPerSlot;

        /* loaded from: classes.dex */
        public static final class Builder {
            private final SparseArray<Player.Commands> allowedPlayerCommandsPerSlot;
            private final SparseArray<SessionCommands> allowedSessionCommandsPerSlot;
            private final SparseBooleanArray areCustomCommandsAllowedPerSlot;
            private boolean buildCalled;
            private final SparseIntArray maxButtonsPerSlot;

            public Builder() {
                SparseIntArray sparseIntArray = new SparseIntArray();
                this.maxButtonsPerSlot = sparseIntArray;
                sparseIntArray.put(1, 1);
                sparseIntArray.put(2, 1);
                sparseIntArray.put(3, 1);
                sparseIntArray.put(6, Integer.MAX_VALUE);
                this.allowedPlayerCommandsPerSlot = new SparseArray<>();
                this.allowedSessionCommandsPerSlot = new SparseArray<>();
                this.areCustomCommandsAllowedPerSlot = new SparseBooleanArray();
            }

            public Builder setMaxButtonsForSlot(int i, int i2) {
                Assertions.checkArgument(i2 >= 0);
                this.maxButtonsPerSlot.put(i, i2);
                return this;
            }

            public Builder setAllowedPlayerCommandsForSlot(int i, Player.Commands commands) {
                this.allowedPlayerCommandsPerSlot.put(i, commands);
                return this;
            }

            public Builder setAllowedSessionCommandsForSlot(int i, SessionCommands sessionCommands) {
                this.allowedSessionCommandsPerSlot.put(i, sessionCommands);
                return this;
            }

            public Builder setAllowCustomCommandsForSlot(int i, boolean z) {
                this.areCustomCommandsAllowedPerSlot.put(i, z);
                return this;
            }

            public DisplayConstraints build() {
                Assertions.checkState(!this.buildCalled);
                this.buildCalled = true;
                return new DisplayConstraints(this);
            }
        }

        private DisplayConstraints(Builder builder) {
            this.maxButtonsPerSlot = builder.maxButtonsPerSlot;
            this.allowedPlayerCommandsPerSlot = builder.allowedPlayerCommandsPerSlot;
            this.allowedSessionCommandsPerSlot = builder.allowedSessionCommandsPerSlot;
            this.areCustomCommandsAllowedPerSlot = builder.areCustomCommandsAllowedPerSlot;
        }

        public ImmutableList<CommandButton> resolve(List<CommandButton> list, Player player) {
            SparseIntArray clone = this.maxButtonsPerSlot.clone();
            ImmutableList.Builder builder = ImmutableList.builder();
            CommandButton commandButton = null;
            CommandButton commandButton2 = null;
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    break;
                }
                CommandButton commandButton3 = list.get(i);
                int i2 = 0;
                while (true) {
                    if (i2 < commandButton3.slots.length()) {
                        int i3 = commandButton3.slots.get(i2);
                        if (reserveSlotForButton(commandButton3, i3, clone)) {
                            builder.add((ImmutableList.Builder) commandButton3.copyWithSlots(ImmutableIntArray.of(i3)));
                            if (commandButton == null && i3 == 3) {
                                commandButton = commandButton3;
                            } else if (commandButton2 == null && i3 == 2) {
                                commandButton2 = commandButton3;
                            }
                        } else {
                            i2++;
                        }
                    }
                }
                i++;
            }
            Player.Commands availableCommands = player.getAvailableCommands();
            if (this.maxButtonsPerSlot.get(1) == clone.get(1)) {
                CommandButton createButton = createButton(Util.shouldShowPlayButton(player) ? CommandButton.ICON_PLAY : CommandButton.ICON_PAUSE, 1, availableCommands);
                if (reserveSlotForButton(createButton, 1, clone)) {
                    builder.add((ImmutableList.Builder) createButton);
                }
            }
            boolean z = commandButton2 == null && this.maxButtonsPerSlot.get(2) > 0;
            boolean z2 = commandButton == null && this.maxButtonsPerSlot.get(3) > 0;
            if (z && z2) {
                int firstAvailableOrFirstCommand = getFirstAvailableOrFirstCommand(availableCommands, 7, 9, 6, 8, 11, 12);
                CommandButton createButton2 = createButton(getIconForPlayerCommand(firstAvailableOrFirstCommand, player), firstAvailableOrFirstCommand, availableCommands);
                int i4 = createButton2.slots.get(0);
                if (reserveSlotForButton(createButton2, i4, clone)) {
                    builder.add((ImmutableList.Builder) createButton2);
                }
                int i5 = i4 != 2 ? 2 : 3;
                CommandButton createOppositeButton = createOppositeButton(createButton2, i5, player);
                if (reserveSlotForButton(createOppositeButton, i5, clone)) {
                    builder.add((ImmutableList.Builder) createOppositeButton);
                }
            } else if (z) {
                CommandButton createOppositeButton2 = createOppositeButton(commandButton, 2, player);
                if (reserveSlotForButton(createOppositeButton2, 2, clone)) {
                    builder.add((ImmutableList.Builder) createOppositeButton2);
                }
            } else if (z2) {
                CommandButton createOppositeButton3 = createOppositeButton(commandButton2, 3, player);
                if (reserveSlotForButton(createOppositeButton3, 3, clone)) {
                    builder.add((ImmutableList.Builder) createOppositeButton3);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
        
            if (r0.contains(r5.playerCommand) == false) goto L20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0045, code lost:
        
            if (r0.contains(r5.sessionCommand) == false) goto L20;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private boolean reserveSlotForButton(androidx.media3.session.CommandButton r5, int r6, android.util.SparseIntArray r7) {
            /*
                r4 = this;
                int r0 = r7.get(r6)
                r1 = 0
                if (r0 != 0) goto L8
                return r1
            L8:
                int r0 = r5.playerCommand
                r2 = -1
                r3 = 1
                if (r0 == r2) goto L22
                android.util.SparseArray<androidx.media3.common.Player$Commands> r0 = r4.allowedPlayerCommandsPerSlot
                java.lang.Object r0 = r0.get(r6)
                androidx.media3.common.Player$Commands r0 = (androidx.media3.common.Player.Commands) r0
                if (r0 == 0) goto L20
                int r5 = r5.playerCommand
                boolean r5 = r0.contains(r5)
                if (r5 == 0) goto L48
            L20:
                r1 = r3
                goto L48
            L22:
                androidx.media3.session.SessionCommand r0 = r5.sessionCommand
                java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)
                androidx.media3.session.SessionCommand r0 = (androidx.media3.session.SessionCommand) r0
                int r0 = r0.commandCode
                if (r0 != 0) goto L35
                android.util.SparseBooleanArray r5 = r4.areCustomCommandsAllowedPerSlot
                boolean r1 = r5.get(r6, r3)
                goto L48
            L35:
                android.util.SparseArray<androidx.media3.session.SessionCommands> r0 = r4.allowedSessionCommandsPerSlot
                java.lang.Object r0 = r0.get(r6)
                androidx.media3.session.SessionCommands r0 = (androidx.media3.session.SessionCommands) r0
                if (r0 == 0) goto L20
                androidx.media3.session.SessionCommand r5 = r5.sessionCommand
                boolean r5 = r0.contains(r5)
                if (r5 == 0) goto L48
                goto L20
            L48:
                if (r1 == 0) goto L52
                int r5 = r7.get(r6)
                int r5 = r5 - r3
                r7.put(r6, r5)
            L52:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.CommandButton.DisplayConstraints.reserveSlotForButton(androidx.media3.session.CommandButton, int, android.util.SparseIntArray):boolean");
        }

        private static CommandButton createOppositeButton(CommandButton commandButton, int i, Player player) {
            Player.Commands availableCommands = player.getAvailableCommands();
            int oppositePlayerCommand = getOppositePlayerCommand(commandButton, i, availableCommands);
            int oppositeIcon = getOppositeIcon(commandButton);
            if (oppositeIcon == 0) {
                oppositeIcon = getIconForPlayerCommand(oppositePlayerCommand, player);
            }
            return createButton(oppositeIcon, oppositePlayerCommand, availableCommands);
        }

        private static CommandButton createButton(int i, int i2, Player.Commands commands) {
            return new Builder(i).setPlayerCommand(i2).setEnabled(commands.contains(i2)).build();
        }

        private static int getFirstAvailableOrFirstCommand(Player.Commands commands, int... iArr) {
            for (int i : iArr) {
                if (commands.contains(i)) {
                    return i;
                }
            }
            return iArr[0];
        }

        private static int getOppositePlayerCommand(CommandButton commandButton, int i, Player.Commands commands) {
            if (commandButton != null) {
                switch (commandButton.playerCommand) {
                    case 6:
                        return 8;
                    case 7:
                        return 9;
                    case 8:
                        return 6;
                    case 9:
                        return 7;
                    case 11:
                        return 12;
                    case 12:
                        return 11;
                }
            }
            if (i == 2) {
                return getFirstAvailableOrFirstCommand(commands, 7, 6, 11);
            }
            return getFirstAvailableOrFirstCommand(commands, 9, 8, 12);
        }

        private static int getOppositeIcon(CommandButton commandButton) {
            if (commandButton == null) {
                return 0;
            }
            switch (commandButton.icon) {
                case CommandButton.ICON_FAST_FORWARD /* 57375 */:
                    return CommandButton.ICON_REWIND;
                case CommandButton.ICON_REWIND /* 57376 */:
                    return CommandButton.ICON_FAST_FORWARD;
                case CommandButton.ICON_SKIP_BACK /* 57410 */:
                    return CommandButton.ICON_SKIP_FORWARD;
                case CommandButton.ICON_NEXT /* 57412 */:
                    return CommandButton.ICON_PREVIOUS;
                case CommandButton.ICON_PREVIOUS /* 57413 */:
                    return CommandButton.ICON_NEXT;
                case CommandButton.ICON_SKIP_FORWARD /* 63220 */:
                    return CommandButton.ICON_SKIP_BACK;
                default:
                    return 0;
            }
        }

        private static int getIconForPlayerCommand(int i, Player player) {
            switch (i) {
                case 6:
                case 7:
                    return CommandButton.ICON_PREVIOUS;
                case 8:
                case 9:
                    return CommandButton.ICON_NEXT;
                case 10:
                default:
                    throw new UnsupportedOperationException();
                case 11:
                    long seekBackIncrement = player.getSeekBackIncrement();
                    return (seekBackIncrement < 2500 || seekBackIncrement >= 7500) ? (seekBackIncrement < 7500 || seekBackIncrement >= 12500) ? (seekBackIncrement < 12500 || seekBackIncrement >= 20000) ? (seekBackIncrement < 20000 || seekBackIncrement >= 40000) ? CommandButton.ICON_SKIP_BACK : CommandButton.ICON_SKIP_BACK_30 : CommandButton.ICON_SKIP_BACK_15 : CommandButton.ICON_SKIP_BACK_10 : CommandButton.ICON_SKIP_BACK_5;
                case 12:
                    long seekForwardIncrement = player.getSeekForwardIncrement();
                    return (seekForwardIncrement < 2500 || seekForwardIncrement >= 7500) ? (seekForwardIncrement < 7500 || seekForwardIncrement >= 12500) ? (seekForwardIncrement < 12500 || seekForwardIncrement >= 20000) ? (seekForwardIncrement < 20000 || seekForwardIncrement >= 40000) ? CommandButton.ICON_SKIP_FORWARD : CommandButton.ICON_SKIP_FORWARD_30 : CommandButton.ICON_SKIP_FORWARD_15 : CommandButton.ICON_SKIP_FORWARD_10 : CommandButton.ICON_SKIP_FORWARD_5;
            }
        }
    }

    private CommandButton(SessionCommand sessionCommand, int i, int i2, int i3, Uri uri, CharSequence charSequence, Bundle bundle, boolean z, ImmutableIntArray immutableIntArray) {
        this.sessionCommand = sessionCommand;
        this.playerCommand = i;
        this.icon = i2;
        this.iconResId = i3;
        this.iconUri = uri;
        this.displayName = charSequence;
        this.extras = new Bundle(bundle);
        this.isEnabled = z;
        this.slots = immutableIntArray;
    }

    @CheckReturnValue
    CommandButton copyWithIsEnabled(boolean z) {
        return this.isEnabled == z ? this : new CommandButton(this.sessionCommand, this.playerCommand, this.icon, this.iconResId, this.iconUri, this.displayName, new Bundle(this.extras), z, this.slots);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CheckReturnValue
    public CommandButton copyWithSlots(ImmutableIntArray immutableIntArray) {
        return this.slots.equals(immutableIntArray) ? this : new CommandButton(this.sessionCommand, this.playerCommand, this.icon, this.iconResId, this.iconUri, this.displayName, new Bundle(this.extras), this.isEnabled, immutableIntArray);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommandButton)) {
            return false;
        }
        CommandButton commandButton = (CommandButton) obj;
        return Objects.equals(this.sessionCommand, commandButton.sessionCommand) && this.playerCommand == commandButton.playerCommand && this.icon == commandButton.icon && this.iconResId == commandButton.iconResId && Objects.equals(this.iconUri, commandButton.iconUri) && TextUtils.equals(this.displayName, commandButton.displayName) && this.isEnabled == commandButton.isEnabled && this.slots.equals(commandButton.slots);
    }

    public int hashCode() {
        return Objects.hash(this.sessionCommand, Integer.valueOf(this.playerCommand), Integer.valueOf(this.icon), Integer.valueOf(this.iconResId), this.displayName, Boolean.valueOf(this.isEnabled), this.iconUri, this.slots);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImmutableList<CommandButton> copyWithUnavailableButtonsDisabled(List<CommandButton> list, SessionCommands sessionCommands, Player.Commands commands) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < list.size(); i++) {
            CommandButton commandButton = list.get(i);
            if (isButtonCommandAvailable(commandButton, sessionCommands, commands)) {
                builder.add((ImmutableList.Builder) commandButton);
            } else {
                builder.add((ImmutableList.Builder) commandButton.copyWithIsEnabled(false));
            }
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isButtonCommandAvailable(CommandButton commandButton, SessionCommands sessionCommands, Player.Commands commands) {
        SessionCommand sessionCommand = commandButton.sessionCommand;
        if (sessionCommand != null && sessionCommands.contains(sessionCommand)) {
            return true;
        }
        int i = commandButton.playerCommand;
        return i != -1 && commands.contains(i);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        SessionCommand sessionCommand = this.sessionCommand;
        if (sessionCommand != null) {
            bundle.putBundle(FIELD_SESSION_COMMAND, sessionCommand.toBundle());
        }
        int i = this.playerCommand;
        if (i != -1) {
            bundle.putInt(FIELD_PLAYER_COMMAND, i);
        }
        int i2 = this.icon;
        if (i2 != 0) {
            bundle.putInt(FIELD_ICON, i2);
        }
        int i3 = this.iconResId;
        if (i3 != 0) {
            bundle.putInt(FIELD_ICON_RES_ID, i3);
        }
        CharSequence charSequence = this.displayName;
        if (charSequence != "") {
            bundle.putCharSequence(FIELD_DISPLAY_NAME, charSequence);
        }
        if (!this.extras.isEmpty()) {
            bundle.putBundle(FIELD_EXTRAS, this.extras);
        }
        Uri uri = this.iconUri;
        if (uri != null) {
            bundle.putParcelable(FIELD_ICON_URI, uri);
        }
        boolean z = this.isEnabled;
        if (!z) {
            bundle.putBoolean(FIELD_ENABLED, z);
        }
        if (this.slots.length() == 1 && this.slots.get(0) == 6) {
            return bundle;
        }
        bundle.putIntArray(FIELD_SLOTS, this.slots.toArray());
        return bundle;
    }

    @Deprecated
    public static CommandButton fromBundle(Bundle bundle) {
        return fromBundle(bundle, 5);
    }

    public static CommandButton fromBundle(Bundle bundle, int i) {
        Bundle bundle2 = bundle.getBundle(FIELD_SESSION_COMMAND);
        SessionCommand fromBundle = bundle2 == null ? null : SessionCommand.fromBundle(bundle2);
        int i2 = bundle.getInt(FIELD_PLAYER_COMMAND, -1);
        int i3 = bundle.getInt(FIELD_ICON_RES_ID, 0);
        CharSequence charSequence = bundle.getCharSequence(FIELD_DISPLAY_NAME, "");
        Bundle bundle3 = bundle.getBundle(FIELD_EXTRAS);
        boolean z = i < 3 || bundle.getBoolean(FIELD_ENABLED, true);
        Uri uri = (Uri) bundle.getParcelable(FIELD_ICON_URI);
        int i4 = bundle.getInt(FIELD_ICON, 0);
        int[] intArray = bundle.getIntArray(FIELD_SLOTS);
        Builder builder = new Builder(i4, i3);
        if (fromBundle != null) {
            builder.setSessionCommand(fromBundle);
        }
        if (i2 != -1) {
            builder.setPlayerCommand(i2);
        }
        if (uri != null && (Objects.equals(uri.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME) || Objects.equals(uri.getScheme(), UriUtil.QUALIFIED_RESOURCE_SCHEME))) {
            builder.setIconUri(uri);
        }
        Builder displayName = builder.setDisplayName(charSequence);
        if (bundle3 == null) {
            bundle3 = Bundle.EMPTY;
        }
        Builder enabled = displayName.setExtras(bundle3).setEnabled(z);
        if (intArray == null) {
            intArray = new int[]{6};
        }
        return enabled.setSlots(intArray).build();
    }

    public static int getIconResIdForIconConstant(int i) {
        switch (i) {
            case ICON_ALBUM /* 57369 */:
                return R.drawable.media3_icon_album;
            case ICON_ARTIST /* 57370 */:
                return R.drawable.media3_icon_artist;
            case ICON_CLOSED_CAPTIONS /* 57372 */:
                return R.drawable.media3_icon_closed_captions;
            case ICON_FAST_FORWARD /* 57375 */:
                return R.drawable.media3_icon_fast_forward;
            case ICON_REWIND /* 57376 */:
                return R.drawable.media3_icon_rewind;
            case ICON_PAUSE /* 57396 */:
                return R.drawable.media3_icon_pause;
            case ICON_PLAY /* 57399 */:
                return R.drawable.media3_icon_play;
            case ICON_PLAYLIST_ADD /* 57403 */:
                return R.drawable.media3_icon_playlist_add;
            case ICON_REPEAT_ALL /* 57408 */:
                return R.drawable.media3_icon_repeat_all;
            case ICON_REPEAT_ONE /* 57409 */:
                return R.drawable.media3_icon_repeat_one;
            case ICON_SKIP_BACK /* 57410 */:
                return R.drawable.media3_icon_skip_back;
            case ICON_SHUFFLE_ON /* 57411 */:
                return R.drawable.media3_icon_shuffle_on;
            case ICON_NEXT /* 57412 */:
                return R.drawable.media3_icon_next;
            case ICON_PREVIOUS /* 57413 */:
                return R.drawable.media3_icon_previous;
            case ICON_STOP /* 57415 */:
                return R.drawable.media3_icon_stop;
            case ICON_SUBTITLES /* 57416 */:
                return R.drawable.media3_icon_subtitles;
            case ICON_VOLUME_DOWN /* 57421 */:
                return R.drawable.media3_icon_volume_down;
            case ICON_VOLUME_OFF /* 57423 */:
                return R.drawable.media3_icon_volume_off;
            case ICON_VOLUME_UP /* 57424 */:
                return R.drawable.media3_icon_volume_up;
            case ICON_SKIP_FORWARD_10 /* 57430 */:
                return R.drawable.media3_icon_skip_forward_10;
            case ICON_SKIP_FORWARD_30 /* 57431 */:
                return R.drawable.media3_icon_skip_forward_30;
            case ICON_SKIP_FORWARD_5 /* 57432 */:
                return R.drawable.media3_icon_skip_forward_5;
            case ICON_SKIP_BACK_10 /* 57433 */:
                return R.drawable.media3_icon_skip_back_10;
            case ICON_SKIP_BACK_30 /* 57434 */:
                return R.drawable.media3_icon_skip_back_30;
            case ICON_SKIP_BACK_5 /* 57435 */:
                return R.drawable.media3_icon_skip_back_5;
            case ICON_QUEUE_ADD /* 57436 */:
                return R.drawable.media3_icon_queue_add;
            case ICON_QUEUE_NEXT /* 57446 */:
                return R.drawable.media3_icon_queue_next;
            case ICON_QUEUE_REMOVE /* 57447 */:
                return R.drawable.media3_icon_queue_remove;
            case ICON_PLAYBACK_SPEED /* 57448 */:
                return R.drawable.media3_icon_playback_speed;
            case ICON_FEED /* 57573 */:
                return R.drawable.media3_icon_feed;
            case ICON_PLUS /* 57669 */:
                return R.drawable.media3_icon_plus;
            case ICON_PLUS_CIRCLE_UNFILLED /* 57671 */:
                return R.drawable.media3_icon_plus_circle_unfilled;
            case ICON_BLOCK /* 57675 */:
                return R.drawable.media3_icon_block;
            case ICON_FLAG_UNFILLED /* 57683 */:
                return R.drawable.media3_icon_flag_unfilled;
            case ICON_MINUS /* 57691 */:
                return R.drawable.media3_icon_minus;
            case ICON_QUALITY /* 58409 */:
                return R.drawable.media3_icon_quality;
            case ICON_RADIO /* 58654 */:
                return R.drawable.media3_icon_radio;
            case ICON_SYNC /* 58919 */:
                return R.drawable.media3_icon_sync;
            case ICON_SHARE /* 59405 */:
                return R.drawable.media3_icon_share;
            case ICON_STAR_UNFILLED /* 59448 */:
                return R.drawable.media3_icon_star_unfilled;
            case ICON_BOOKMARK_UNFILLED /* 59494 */:
                return R.drawable.media3_icon_bookmark_unfilled;
            case ICON_CHECK_CIRCLE_UNFILLED /* 59500 */:
                return R.drawable.media3_icon_check_circle_unfilled;
            case ICON_HEART_UNFILLED /* 59517 */:
                return R.drawable.media3_icon_heart_unfilled;
            case ICON_SETTINGS /* 59576 */:
                return R.drawable.media3_icon_settings;
            case ICON_THUMB_DOWN_UNFILLED /* 59611 */:
                return R.drawable.media3_icon_thumb_down_unfilled;
            case ICON_THUMB_UP_UNFILLED /* 59612 */:
                return R.drawable.media3_icon_thumb_up_unfilled;
            case ICON_PLAYLIST_REMOVE /* 60288 */:
                return R.drawable.media3_icon_playlist_remove;
            case ICON_SUBTITLES_OFF /* 61298 */:
                return R.drawable.media3_icon_subtitles_off;
            case ICON_PLAYBACK_SPEED_1_0 /* 61389 */:
                return R.drawable.media3_icon_playback_speed_1_0;
            case ICON_SIGNAL /* 61512 */:
                return R.drawable.media3_icon_signal;
            case ICON_CLOSED_CAPTIONS_OFF /* 61916 */:
                return R.drawable.media3_icon_closed_captions_off;
            case ICON_PLAYBACK_SPEED_1_5 /* 62688 */:
                return R.drawable.media3_icon_playback_speed_1_5;
            case ICON_PLAYBACK_SPEED_1_2 /* 62689 */:
                return R.drawable.media3_icon_playback_speed_1_2;
            case ICON_PLAYBACK_SPEED_0_5 /* 62690 */:
                return R.drawable.media3_icon_playback_speed_0_5;
            case ICON_PLAYBACK_SPEED_2_0 /* 62699 */:
                return R.drawable.media3_icon_playback_speed_2_0;
            case ICON_SKIP_FORWARD /* 63220 */:
                return R.drawable.media3_icon_skip_forward;
            case ICON_REPEAT_OFF /* 1040448 */:
                return R.drawable.media3_icon_repeat_off;
            case ICON_SHUFFLE_STAR /* 1040451 */:
                return R.drawable.media3_icon_shuffle_star;
            case ICON_SHUFFLE_OFF /* 1040452 */:
                return R.drawable.media3_icon_shuffle_off;
            case ICON_SKIP_FORWARD_15 /* 1040470 */:
                return R.drawable.media3_icon_skip_forward_15;
            case ICON_SKIP_BACK_15 /* 1040473 */:
                return R.drawable.media3_icon_skip_back_15;
            case ICON_PLUS_CIRCLE_FILLED /* 1040711 */:
                return R.drawable.media3_icon_plus_circle_filled;
            case ICON_MINUS_CIRCLE_FILLED /* 1040712 */:
                return R.drawable.media3_icon_minus_circle_filled;
            case ICON_MINUS_CIRCLE_UNFILLED /* 1040713 */:
                return R.drawable.media3_icon_minus_circle_unfilled;
            case ICON_FLAG_FILLED /* 1040723 */:
                return R.drawable.media3_icon_flag_filled;
            case ICON_STAR_FILLED /* 1042488 */:
                return R.drawable.media3_icon_star_filled;
            case ICON_BOOKMARK_FILLED /* 1042534 */:
                return R.drawable.media3_icon_bookmark_filled;
            case ICON_CHECK_CIRCLE_FILLED /* 1042540 */:
                return R.drawable.media3_icon_check_circle_filled;
            case ICON_HEART_FILLED /* 1042557 */:
                return R.drawable.media3_icon_heart_filled;
            case ICON_THUMB_DOWN_FILLED /* 1042651 */:
                return R.drawable.media3_icon_thumb_down_filled;
            case ICON_THUMB_UP_FILLED /* 1042652 */:
                return R.drawable.media3_icon_thumb_up_filled;
            case ICON_PLAYBACK_SPEED_1_8 /* 1045728 */:
                return R.drawable.media3_icon_playback_speed_1_8;
            case ICON_PLAYBACK_SPEED_0_8 /* 1045730 */:
                return R.drawable.media3_icon_playback_speed_0_8;
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImmutableList<CommandButton> getCustomLayoutFromMediaButtonPreferences(List<CommandButton> list, boolean z, boolean z2) {
        SessionCommand sessionCommand;
        SessionCommand sessionCommand2;
        int i;
        if (list.isEmpty()) {
            return ImmutableList.of();
        }
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < list.size(); i4++) {
            CommandButton commandButton = list.get(i4);
            if (commandButton.isEnabled && (sessionCommand2 = commandButton.sessionCommand) != null && sessionCommand2.commandCode == 0) {
                int i5 = 0;
                while (true) {
                    if (i5 < commandButton.slots.length() && (i = commandButton.slots.get(i5)) != 6) {
                        if (z && i2 == -1 && i == 2) {
                            i2 = i4;
                            break;
                        }
                        if (z2 && i3 == -1 && i == 3) {
                            i3 = i4;
                            break;
                        }
                        i5++;
                    }
                }
            }
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        if (i2 != -1) {
            builder.add((ImmutableList.Builder) list.get(i2).copyWithSlots(ImmutableIntArray.of(2)));
        }
        if (i3 != -1) {
            builder.add((ImmutableList.Builder) list.get(i3).copyWithSlots(ImmutableIntArray.of(3)));
        }
        for (int i6 = 0; i6 < list.size(); i6++) {
            CommandButton commandButton2 = list.get(i6);
            if (commandButton2.isEnabled && (sessionCommand = commandButton2.sessionCommand) != null && sessionCommand.commandCode == 0 && i6 != i2 && i6 != i3 && commandButton2.slots.contains(6)) {
                builder.add((ImmutableList.Builder) commandButton2.copyWithSlots(ImmutableIntArray.of(6)));
            }
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean containsButtonForSlot(List<CommandButton> list, int i) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).slots.get(0) == i) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImmutableList<CommandButton> getMediaButtonPreferencesFromCustomLayout(List<CommandButton> list, Player.Commands commands, Bundle bundle) {
        if (list.isEmpty()) {
            return ImmutableList.of();
        }
        boolean containsAny = commands.containsAny(7, 6);
        boolean containsAny2 = commands.containsAny(9, 8);
        boolean z = bundle.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false);
        boolean z2 = bundle.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false);
        int i = (containsAny || z) ? -1 : 0;
        int i2 = (containsAny2 || z2) ? -1 : i == 0 ? 1 : 0;
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i3 = 0; i3 < list.size(); i3++) {
            CommandButton commandButton = list.get(i3);
            if (i3 == i) {
                if (i2 == -1) {
                    builder.add((ImmutableList.Builder) commandButton.copyWithSlots(ImmutableIntArray.of(2, 6)));
                } else {
                    builder.add((ImmutableList.Builder) commandButton.copyWithSlots(ImmutableIntArray.of(2, 3, 6)));
                }
            } else if (i3 == i2) {
                builder.add((ImmutableList.Builder) commandButton.copyWithSlots(ImmutableIntArray.of(3, 6)));
            } else {
                builder.add((ImmutableList.Builder) commandButton.copyWithSlots(ImmutableIntArray.of(6)));
            }
        }
        return builder.build();
    }
}
