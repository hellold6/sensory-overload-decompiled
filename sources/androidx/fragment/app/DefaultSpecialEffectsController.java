package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultSpecialEffectsController.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0003*+,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J(\u0010\t\u001a\u00020\u00062\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u0006\u0010\u000e\u001a\u00020\fH\u0002J\u001e\u0010\u000f\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J$\u0010\u0014\u001a\u00020\u00062\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\f0\u00162\u0006\u0010\u000e\u001a\u00020\fH\u0002J@\u0010\u0018\u001a\u00020\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00112\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u001c2\u0006\u0010\u001d\u001a\u00020\u00132\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00130\u001fH\u0002JL\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00130\u001f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00112\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u001c2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010#\u001a\u0004\u0018\u00010\b2\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002J\u0016\u0010%\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011H\u0002J&\u0010&\u001a\u00020\u0006*\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\f0'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00170)H\u0002¨\u0006-"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController;", "Landroidx/fragment/app/SpecialEffectsController;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "applyContainerChanges", "", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "captureTransitioningViews", "transitioningViews", "Ljava/util/ArrayList;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "view", "executeOperations", "operations", "", "isPop", "", "findNamedViews", "namedViews", "", "", "startAnimations", "animationInfos", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "awaitingContainerChanges", "", "startedAnyTransition", "startedTransitions", "", "startTransitions", "transitionInfos", "Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "firstOut", "lastIn", "syncAnimations", "retainMatchingViews", "Landroidx/collection/ArrayMap;", "names", "", "AnimationInfo", "SpecialEffectsInfo", "TransitionInfo", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DefaultSpecialEffectsController extends SpecialEffectsController {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController(ViewGroup container) {
        super(container);
        Intrinsics.checkNotNullParameter(container, "container");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v2 */
    @Override // androidx.fragment.app.SpecialEffectsController
    public void executeOperations(List<? extends SpecialEffectsController.Operation> operations, boolean isPop) {
        SpecialEffectsController.Operation operation;
        SpecialEffectsController.Operation operation2;
        Intrinsics.checkNotNullParameter(operations, "operations");
        Iterator it = operations.iterator();
        while (true) {
            operation = null;
            if (!it.hasNext()) {
                operation2 = 0;
                break;
            }
            operation2 = it.next();
            SpecialEffectsController.Operation operation3 = (SpecialEffectsController.Operation) operation2;
            SpecialEffectsController.Operation.State.Companion companion = SpecialEffectsController.Operation.State.INSTANCE;
            View view = operation3.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view, "operation.fragment.mView");
            if (companion.asOperationState(view) == SpecialEffectsController.Operation.State.VISIBLE && operation3.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE) {
                break;
            }
        }
        SpecialEffectsController.Operation operation4 = operation2;
        ListIterator<? extends SpecialEffectsController.Operation> listIterator = operations.listIterator(operations.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                break;
            }
            SpecialEffectsController.Operation previous = listIterator.previous();
            SpecialEffectsController.Operation operation5 = previous;
            SpecialEffectsController.Operation.State.Companion companion2 = SpecialEffectsController.Operation.State.INSTANCE;
            View view2 = operation5.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view2, "operation.fragment.mView");
            if (companion2.asOperationState(view2) != SpecialEffectsController.Operation.State.VISIBLE && operation5.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                operation = previous;
                break;
            }
        }
        SpecialEffectsController.Operation operation6 = operation;
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Executing operations from " + operation4 + " to " + operation6);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final List<SpecialEffectsController.Operation> mutableList = CollectionsKt.toMutableList((Collection) operations);
        syncAnimations(operations);
        Iterator<? extends SpecialEffectsController.Operation> it2 = operations.iterator();
        while (true) {
            boolean z = true;
            if (!it2.hasNext()) {
                break;
            }
            final SpecialEffectsController.Operation next = it2.next();
            CancellationSignal cancellationSignal = new CancellationSignal();
            next.markStartedSpecialEffect(cancellationSignal);
            arrayList.add(new AnimationInfo(next, cancellationSignal, isPop));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            next.markStartedSpecialEffect(cancellationSignal2);
            if (isPop) {
                if (next == operation4) {
                    arrayList2.add(new TransitionInfo(next, cancellationSignal2, isPop, z));
                    next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSpecialEffectsController.executeOperations$lambda$2(mutableList, next, this);
                        }
                    });
                }
                z = false;
                arrayList2.add(new TransitionInfo(next, cancellationSignal2, isPop, z));
                next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultSpecialEffectsController.executeOperations$lambda$2(mutableList, next, this);
                    }
                });
            } else {
                if (next == operation6) {
                    arrayList2.add(new TransitionInfo(next, cancellationSignal2, isPop, z));
                    next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSpecialEffectsController.executeOperations$lambda$2(mutableList, next, this);
                        }
                    });
                }
                z = false;
                arrayList2.add(new TransitionInfo(next, cancellationSignal2, isPop, z));
                next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultSpecialEffectsController.executeOperations$lambda$2(mutableList, next, this);
                    }
                });
            }
        }
        Map<SpecialEffectsController.Operation, Boolean> startTransitions = startTransitions(arrayList2, mutableList, isPop, operation4, operation6);
        startAnimations(arrayList, mutableList, startTransitions.containsValue(true), startTransitions);
        Iterator<SpecialEffectsController.Operation> it3 = mutableList.iterator();
        while (it3.hasNext()) {
            applyContainerChanges(it3.next());
        }
        mutableList.clear();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Completed executing operations from " + operation4 + " to " + operation6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void executeOperations$lambda$2(List awaitingContainerChanges, SpecialEffectsController.Operation operation, DefaultSpecialEffectsController this$0) {
        Intrinsics.checkNotNullParameter(awaitingContainerChanges, "$awaitingContainerChanges");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (awaitingContainerChanges.contains(operation)) {
            awaitingContainerChanges.remove(operation);
            this$0.applyContainerChanges(operation);
        }
    }

    private final void syncAnimations(List<? extends SpecialEffectsController.Operation> operations) {
        Fragment fragment = ((SpecialEffectsController.Operation) CollectionsKt.last((List) operations)).getFragment();
        for (SpecialEffectsController.Operation operation : operations) {
            operation.getFragment().mAnimationInfo.mEnterAnim = fragment.mAnimationInfo.mEnterAnim;
            operation.getFragment().mAnimationInfo.mExitAnim = fragment.mAnimationInfo.mExitAnim;
            operation.getFragment().mAnimationInfo.mPopEnterAnim = fragment.mAnimationInfo.mPopEnterAnim;
            operation.getFragment().mAnimationInfo.mPopExitAnim = fragment.mAnimationInfo.mPopExitAnim;
        }
    }

    private final void startAnimations(List<AnimationInfo> animationInfos, List<SpecialEffectsController.Operation> awaitingContainerChanges, boolean startedAnyTransition, Map<SpecialEffectsController.Operation, Boolean> startedTransitions) {
        Context context = getContainer().getContext();
        ArrayList<AnimationInfo> arrayList = new ArrayList();
        boolean z = false;
        for (final AnimationInfo animationInfo : animationInfos) {
            if (animationInfo.isVisibilityUnchanged()) {
                animationInfo.completeSpecialEffect();
            } else {
                Intrinsics.checkNotNullExpressionValue(context, "context");
                FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
                if (animation == null) {
                    animationInfo.completeSpecialEffect();
                } else {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(animationInfo);
                    } else {
                        final SpecialEffectsController.Operation operation = animationInfo.getOperation();
                        Fragment fragment = operation.getFragment();
                        if (Intrinsics.areEqual((Object) startedTransitions.get(operation), (Object) true)) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo.completeSpecialEffect();
                        } else {
                            final boolean z2 = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            if (z2) {
                                awaitingContainerChanges.remove(operation);
                            }
                            final View view = fragment.mView;
                            getContainer().startViewTransition(view);
                            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$startAnimations$1
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator anim) {
                                    Intrinsics.checkNotNullParameter(anim, "anim");
                                    DefaultSpecialEffectsController.this.getContainer().endViewTransition(view);
                                    if (z2) {
                                        SpecialEffectsController.Operation.State finalState = operation.getFinalState();
                                        View viewToAnimate = view;
                                        Intrinsics.checkNotNullExpressionValue(viewToAnimate, "viewToAnimate");
                                        finalState.applyState(viewToAnimate);
                                    }
                                    animationInfo.completeSpecialEffect();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has ended.");
                                    }
                                }
                            });
                            animator.setTarget(view);
                            animator.start();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has started.");
                            }
                            animationInfo.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda0
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public final void onCancel() {
                                    DefaultSpecialEffectsController.startAnimations$lambda$3(animator, operation);
                                }
                            });
                            z = true;
                        }
                    }
                }
            }
        }
        for (final AnimationInfo animationInfo2 : arrayList) {
            final SpecialEffectsController.Operation operation2 = animationInfo2.getOperation();
            Fragment fragment2 = operation2.getFragment();
            if (startedAnyTransition) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo2.completeSpecialEffect();
            } else if (z) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo2.completeSpecialEffect();
            } else {
                final View view2 = fragment2.mView;
                Intrinsics.checkNotNullExpressionValue(context, "context");
                FragmentAnim.AnimationOrAnimator animation2 = animationInfo2.getAnimation(context);
                if (animation2 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                Animation animation3 = animation2.animation;
                if (animation3 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                if (operation2.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view2.startAnimation(animation3);
                    animationInfo2.completeSpecialEffect();
                } else {
                    getContainer().startViewTransition(view2);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation3, getContainer(), view2);
                    endViewTransitionAnimation.setAnimationListener(new DefaultSpecialEffectsController$startAnimations$3(operation2, this, view2, animationInfo2));
                    view2.startAnimation(endViewTransitionAnimation);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v(FragmentManager.TAG, "Animation from operation " + operation2 + " has started.");
                    }
                }
                animationInfo2.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda1
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public final void onCancel() {
                        DefaultSpecialEffectsController.startAnimations$lambda$4(view2, this, animationInfo2, operation2);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startAnimations$lambda$3(Animator animator, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(operation, "$operation");
        animator.end();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has been canceled.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startAnimations$lambda$4(View view, DefaultSpecialEffectsController this$0, AnimationInfo animationInfo, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(animationInfo, "$animationInfo");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        view.clearAnimation();
        this$0.getContainer().endViewTransition(view);
        animationInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Animation from operation " + operation + " has been cancelled.");
        }
    }

    private final Map<SpecialEffectsController.Operation, Boolean> startTransitions(List<TransitionInfo> transitionInfos, List<SpecialEffectsController.Operation> awaitingContainerChanges, final boolean isPop, final SpecialEffectsController.Operation firstOut, SpecialEffectsController.Operation lastIn) {
        Object obj;
        String str;
        String str2;
        Object obj2;
        View view;
        Object obj3;
        View view2;
        Object obj4;
        LinkedHashMap linkedHashMap;
        String str3;
        ArrayList<View> arrayList;
        Object obj5;
        SpecialEffectsController.Operation operation;
        ArrayList<View> arrayList2;
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        Object obj6;
        int i;
        View view3;
        final Rect rect;
        final View view4;
        DefaultSpecialEffectsController defaultSpecialEffectsController = this;
        final SpecialEffectsController.Operation operation2 = lastIn;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        List<TransitionInfo> list = transitionInfos;
        ArrayList arrayList5 = new ArrayList();
        for (Object obj7 : list) {
            if (!((TransitionInfo) obj7).isVisibilityUnchanged()) {
                arrayList5.add(obj7);
            }
        }
        ArrayList<TransitionInfo> arrayList6 = new ArrayList();
        for (Object obj8 : arrayList5) {
            if (((TransitionInfo) obj8).getHandlingImpl() != null) {
                arrayList6.add(obj8);
            }
        }
        final FragmentTransitionImpl fragmentTransitionImpl = null;
        for (TransitionInfo transitionInfo : arrayList6) {
            FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
            if (fragmentTransitionImpl != null && handlingImpl != fragmentTransitionImpl) {
                throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition type than other Fragments.").toString());
            }
            fragmentTransitionImpl = handlingImpl;
        }
        if (fragmentTransitionImpl == null) {
            for (TransitionInfo transitionInfo2 : transitionInfos) {
                linkedHashMap2.put(transitionInfo2.getOperation(), false);
                transitionInfo2.completeSpecialEffect();
            }
            return linkedHashMap2;
        }
        View view5 = new View(defaultSpecialEffectsController.getContainer().getContext());
        Rect rect2 = new Rect();
        ArrayList<View> arrayList7 = new ArrayList<>();
        ArrayList<View> arrayList8 = new ArrayList<>();
        ArrayMap arrayMap = new ArrayMap();
        Iterator<TransitionInfo> it = transitionInfos.iterator();
        boolean z = false;
        Object obj9 = null;
        View view6 = null;
        while (true) {
            boolean hasNext = it.hasNext();
            obj = obj9;
            str = FragmentManager.TAG;
            if (!hasNext) {
                break;
            }
            TransitionInfo next = it.next();
            if (!next.hasSharedElementTransition() || firstOut == null || operation2 == null) {
                arrayList2 = arrayList7;
                view5 = view5;
                rect2 = rect2;
                linkedHashMap2 = linkedHashMap2;
                list = list;
                obj9 = obj;
                view6 = view6;
                arrayMap = arrayMap;
            } else {
                Object wrapTransitionInSet = fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(next.getSharedElementTransition()));
                ArrayList<String> sharedElementSourceNames = operation2.getFragment().getSharedElementSourceNames();
                List<TransitionInfo> list2 = list;
                Intrinsics.checkNotNullExpressionValue(sharedElementSourceNames, "lastIn.fragment.sharedElementSourceNames");
                ArrayList<String> sharedElementSourceNames2 = firstOut.getFragment().getSharedElementSourceNames();
                View view7 = view6;
                Intrinsics.checkNotNullExpressionValue(sharedElementSourceNames2, "firstOut.fragment.sharedElementSourceNames");
                ArrayList<String> sharedElementTargetNames = firstOut.getFragment().getSharedElementTargetNames();
                LinkedHashMap linkedHashMap3 = linkedHashMap2;
                Intrinsics.checkNotNullExpressionValue(sharedElementTargetNames, "firstOut.fragment.sharedElementTargetNames");
                int size = sharedElementTargetNames.size();
                View view8 = view5;
                Rect rect3 = rect2;
                int i2 = 0;
                while (i2 < size) {
                    int i3 = size;
                    int indexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i2));
                    if (indexOf != -1) {
                        sharedElementSourceNames.set(indexOf, sharedElementSourceNames2.get(i2));
                    }
                    i2++;
                    size = i3;
                }
                ArrayList<String> sharedElementTargetNames2 = operation2.getFragment().getSharedElementTargetNames();
                Intrinsics.checkNotNullExpressionValue(sharedElementTargetNames2, "lastIn.fragment.sharedElementTargetNames");
                Pair pair = !isPop ? TuplesKt.to(firstOut.getFragment().getExitTransitionCallback(), operation2.getFragment().getEnterTransitionCallback()) : TuplesKt.to(firstOut.getFragment().getEnterTransitionCallback(), operation2.getFragment().getExitTransitionCallback());
                SharedElementCallback sharedElementCallback = (SharedElementCallback) pair.component1();
                SharedElementCallback sharedElementCallback2 = (SharedElementCallback) pair.component2();
                int i4 = 0;
                for (int size2 = sharedElementSourceNames.size(); i4 < size2; size2 = size2) {
                    arrayMap.put(sharedElementSourceNames.get(i4), sharedElementTargetNames2.get(i4));
                    i4++;
                    arrayList8 = arrayList8;
                }
                ArrayList<View> arrayList9 = arrayList8;
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, ">>> entering view names <<<");
                    for (Iterator<String> it2 = sharedElementTargetNames2.iterator(); it2.hasNext(); it2 = it2) {
                        Log.v(FragmentManager.TAG, "Name: " + it2.next());
                    }
                    Log.v(FragmentManager.TAG, ">>> exiting view names <<<");
                    for (Iterator<String> it3 = sharedElementSourceNames.iterator(); it3.hasNext(); it3 = it3) {
                        Log.v(FragmentManager.TAG, "Name: " + it3.next());
                    }
                }
                ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
                ArrayMap<String, View> arrayMap3 = arrayMap2;
                View view9 = firstOut.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view9, "firstOut.fragment.mView");
                defaultSpecialEffectsController.findNamedViews(arrayMap3, view9);
                ArrayList<String> arrayList10 = sharedElementSourceNames;
                arrayMap2.retainAll(arrayList10);
                if (sharedElementCallback != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        arrayList3 = arrayList10;
                        Log.v(FragmentManager.TAG, "Executing exit callback for operation " + firstOut);
                    } else {
                        arrayList3 = arrayList10;
                    }
                    sharedElementCallback.onMapSharedElements(sharedElementSourceNames, arrayMap3);
                    int size3 = sharedElementSourceNames.size() - 1;
                    if (size3 >= 0) {
                        while (true) {
                            int i5 = size3 - 1;
                            String str4 = sharedElementSourceNames.get(size3);
                            View view10 = arrayMap2.get(str4);
                            if (view10 == null) {
                                arrayMap.remove(str4);
                            } else if (!Intrinsics.areEqual(str4, ViewCompat.getTransitionName(view10))) {
                                arrayMap.put(ViewCompat.getTransitionName(view10), (String) arrayMap.remove(str4));
                            }
                            if (i5 < 0) {
                                break;
                            }
                            size3 = i5;
                        }
                    }
                } else {
                    arrayList3 = arrayList10;
                    arrayMap.retainAll(arrayMap2.keySet());
                }
                final ArrayMap<String, View> arrayMap4 = new ArrayMap<>();
                ArrayMap<String, View> arrayMap5 = arrayMap4;
                View view11 = operation2.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view11, "lastIn.fragment.mView");
                defaultSpecialEffectsController.findNamedViews(arrayMap5, view11);
                ArrayList<String> arrayList11 = sharedElementTargetNames2;
                arrayMap4.retainAll(arrayList11);
                arrayMap4.retainAll(arrayMap.values());
                if (sharedElementCallback2 != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        arrayList4 = arrayList11;
                        Log.v(FragmentManager.TAG, "Executing enter callback for operation " + operation2);
                    } else {
                        arrayList4 = arrayList11;
                    }
                    sharedElementCallback2.onMapSharedElements(sharedElementTargetNames2, arrayMap5);
                    int size4 = sharedElementTargetNames2.size() - 1;
                    if (size4 >= 0) {
                        while (true) {
                            int i6 = size4 - 1;
                            String name = sharedElementTargetNames2.get(size4);
                            View view12 = arrayMap4.get(name);
                            if (view12 == null) {
                                Intrinsics.checkNotNullExpressionValue(name, "name");
                                String findKeyForValue = FragmentTransition.findKeyForValue(arrayMap, name);
                                if (findKeyForValue != null) {
                                    arrayMap.remove(findKeyForValue);
                                }
                            } else if (!Intrinsics.areEqual(name, ViewCompat.getTransitionName(view12))) {
                                Intrinsics.checkNotNullExpressionValue(name, "name");
                                String findKeyForValue2 = FragmentTransition.findKeyForValue(arrayMap, name);
                                if (findKeyForValue2 != null) {
                                    arrayMap.put(findKeyForValue2, ViewCompat.getTransitionName(view12));
                                }
                            }
                            if (i6 < 0) {
                                break;
                            }
                            size4 = i6;
                        }
                    }
                } else {
                    arrayList4 = arrayList11;
                    FragmentTransition.retainValues(arrayMap, arrayMap4);
                }
                Set keySet = arrayMap.keySet();
                Intrinsics.checkNotNullExpressionValue(keySet, "sharedElementNameMapping.keys");
                defaultSpecialEffectsController.retainMatchingViews(arrayMap2, keySet);
                Collection<String> values = arrayMap.values();
                Intrinsics.checkNotNullExpressionValue(values, "sharedElementNameMapping.values");
                defaultSpecialEffectsController.retainMatchingViews(arrayMap4, values);
                if (arrayMap.isEmpty()) {
                    arrayList7.clear();
                    arrayList9.clear();
                    list = list2;
                    view6 = view7;
                    linkedHashMap2 = linkedHashMap3;
                    view5 = view8;
                    rect2 = rect3;
                    arrayList8 = arrayList9;
                    obj9 = null;
                } else {
                    FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), firstOut.getFragment(), isPop, arrayMap2, true);
                    OneShotPreDrawListener.add(defaultSpecialEffectsController.getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSpecialEffectsController.startTransitions$lambda$9(SpecialEffectsController.Operation.this, firstOut, isPop, arrayMap4);
                        }
                    });
                    arrayList7.addAll(arrayMap2.values());
                    if (arrayList3.isEmpty()) {
                        obj6 = wrapTransitionInSet;
                        i = 0;
                        view3 = view7;
                    } else {
                        i = 0;
                        view3 = arrayMap2.get(sharedElementSourceNames.get(0));
                        obj6 = wrapTransitionInSet;
                        fragmentTransitionImpl.setEpicenter(obj6, view3);
                    }
                    arrayList8 = arrayList9;
                    arrayList8.addAll(arrayMap4.values());
                    if (arrayList4.isEmpty() || (view4 = arrayMap4.get(sharedElementTargetNames2.get(i))) == null) {
                        rect = rect3;
                    } else {
                        rect = rect3;
                        OneShotPreDrawListener.add(defaultSpecialEffectsController.getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda4
                            @Override // java.lang.Runnable
                            public final void run() {
                                DefaultSpecialEffectsController.startTransitions$lambda$10(FragmentTransitionImpl.this, view4, rect);
                            }
                        });
                        z = true;
                    }
                    fragmentTransitionImpl.setSharedElementTargets(obj6, view8, arrayList7);
                    arrayList2 = arrayList7;
                    fragmentTransitionImpl.scheduleRemoveTargets(obj6, null, null, null, null, obj6, arrayList8);
                    linkedHashMap3.put(firstOut, true);
                    linkedHashMap3.put(operation2, true);
                    rect2 = rect;
                    view6 = view3;
                    obj9 = obj6;
                    list = list2;
                    arrayMap = arrayMap;
                    view5 = view8;
                    linkedHashMap2 = linkedHashMap3;
                }
            }
            arrayList7 = arrayList2;
        }
        List<TransitionInfo> list3 = list;
        Rect rect4 = rect2;
        ArrayList<View> arrayList12 = arrayList7;
        ArrayMap arrayMap6 = arrayMap;
        View view13 = view6;
        LinkedHashMap linkedHashMap4 = linkedHashMap2;
        View view14 = view5;
        ArrayList arrayList13 = new ArrayList();
        Iterator<TransitionInfo> it4 = transitionInfos.iterator();
        Object obj10 = null;
        Object obj11 = null;
        while (it4.hasNext()) {
            TransitionInfo next2 = it4.next();
            if (next2.isVisibilityUnchanged()) {
                linkedHashMap4.put(next2.getOperation(), false);
                next2.completeSpecialEffect();
            } else {
                Object cloneTransition = fragmentTransitionImpl.cloneTransition(next2.getTransition());
                SpecialEffectsController.Operation operation3 = next2.getOperation();
                boolean z2 = obj != null && (operation3 == firstOut || operation3 == operation2);
                if (cloneTransition != null) {
                    LinkedHashMap linkedHashMap5 = linkedHashMap4;
                    final ArrayList<View> arrayList14 = new ArrayList<>();
                    ArrayList<View> arrayList15 = arrayList12;
                    View view15 = operation3.getFragment().mView;
                    Iterator<TransitionInfo> it5 = it4;
                    Intrinsics.checkNotNullExpressionValue(view15, "operation.fragment.mView");
                    defaultSpecialEffectsController.captureTransitioningViews(arrayList14, view15);
                    if (z2) {
                        if (operation3 == firstOut) {
                            arrayList14.removeAll(CollectionsKt.toSet(arrayList15));
                        } else {
                            arrayList14.removeAll(CollectionsKt.toSet(arrayList8));
                        }
                    }
                    if (arrayList14.isEmpty()) {
                        fragmentTransitionImpl.addTarget(cloneTransition, view14);
                        Object obj12 = obj;
                        view = view14;
                        obj3 = obj12;
                        obj4 = obj10;
                        obj5 = obj11;
                        obj2 = cloneTransition;
                        str3 = str;
                        operation = operation3;
                        view2 = view13;
                        linkedHashMap = linkedHashMap5;
                        arrayList = arrayList8;
                    } else {
                        fragmentTransitionImpl.addTargets(cloneTransition, arrayList14);
                        Object obj13 = obj10;
                        obj2 = cloneTransition;
                        Object obj14 = obj;
                        view = view14;
                        obj3 = obj14;
                        view2 = view13;
                        obj4 = obj13;
                        linkedHashMap = linkedHashMap5;
                        str3 = str;
                        arrayList = arrayList8;
                        obj5 = obj11;
                        fragmentTransitionImpl.scheduleRemoveTargets(obj2, obj2, arrayList14, null, null, null, null);
                        if (operation3.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation = operation3;
                            awaitingContainerChanges.remove(operation);
                            ArrayList<View> arrayList16 = new ArrayList<>(arrayList14);
                            arrayList16.remove(operation.getFragment().mView);
                            fragmentTransitionImpl.scheduleHideFragmentView(obj2, operation.getFragment().mView, arrayList16);
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda5
                                @Override // java.lang.Runnable
                                public final void run() {
                                    DefaultSpecialEffectsController.startTransitions$lambda$11(arrayList14);
                                }
                            });
                        } else {
                            operation = operation3;
                        }
                    }
                    if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList13.addAll(arrayList14);
                        if (z) {
                            fragmentTransitionImpl.setEpicenter(obj2, rect4);
                        }
                    } else {
                        fragmentTransitionImpl.setEpicenter(obj2, view2);
                    }
                    linkedHashMap.put(operation, true);
                    if (next2.getIsOverlapAllowed()) {
                        obj10 = fragmentTransitionImpl.mergeTransitionsTogether(obj4, obj2, null);
                        View view16 = view;
                        obj = obj3;
                        view14 = view16;
                        linkedHashMap4 = linkedHashMap;
                    } else {
                        obj5 = fragmentTransitionImpl.mergeTransitionsTogether(obj5, obj2, null);
                        View view17 = view;
                        obj = obj3;
                        view14 = view17;
                        linkedHashMap4 = linkedHashMap;
                        obj10 = obj4;
                    }
                    obj11 = obj5;
                    arrayList8 = arrayList;
                    arrayList12 = arrayList15;
                    str = str3;
                    defaultSpecialEffectsController = this;
                    it4 = it5;
                    view13 = view2;
                    operation2 = lastIn;
                } else if (!z2) {
                    linkedHashMap4.put(operation3, false);
                    next2.completeSpecialEffect();
                }
            }
        }
        ArrayList<View> arrayList17 = arrayList12;
        LinkedHashMap linkedHashMap6 = linkedHashMap4;
        String str5 = str;
        ArrayList<View> arrayList18 = arrayList8;
        Object obj15 = obj;
        Object mergeTransitionsInSequence = fragmentTransitionImpl.mergeTransitionsInSequence(obj10, obj11, obj15);
        if (mergeTransitionsInSequence == null) {
            return linkedHashMap6;
        }
        ArrayList<TransitionInfo> arrayList19 = new ArrayList();
        for (Object obj16 : list3) {
            if (!((TransitionInfo) obj16).isVisibilityUnchanged()) {
                arrayList19.add(obj16);
            }
        }
        for (final TransitionInfo transitionInfo3 : arrayList19) {
            Object transition = transitionInfo3.getTransition();
            final SpecialEffectsController.Operation operation4 = transitionInfo3.getOperation();
            boolean z3 = obj15 != null && (operation4 == firstOut || operation4 == lastIn);
            if (transition == null && !z3) {
                str2 = str5;
            } else if (ViewCompat.isLaidOut(getContainer())) {
                str2 = str5;
                fragmentTransitionImpl.setListenerForTransitionEnd(transitionInfo3.getOperation().getFragment(), mergeTransitionsInSequence, transitionInfo3.getSignal(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultSpecialEffectsController.startTransitions$lambda$14$lambda$13(DefaultSpecialEffectsController.TransitionInfo.this, operation4);
                    }
                });
            } else {
                if (FragmentManager.isLoggingEnabled(2)) {
                    str2 = str5;
                    Log.v(str2, "SpecialEffectsController: Container " + getContainer() + " has not been laid out. Completing operation " + operation4);
                } else {
                    str2 = str5;
                }
                transitionInfo3.completeSpecialEffect();
            }
            str5 = str2;
        }
        String str6 = str5;
        if (!ViewCompat.isLaidOut(getContainer())) {
            return linkedHashMap6;
        }
        ArrayList arrayList20 = arrayList13;
        FragmentTransition.setViewVisibility(arrayList20, 4);
        ArrayList<String> prepareSetNameOverridesReordered = fragmentTransitionImpl.prepareSetNameOverridesReordered(arrayList18);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(str6, ">>>>> Beginning transition <<<<<");
            Log.v(str6, ">>>>> SharedElementFirstOutViews <<<<<");
            Iterator<View> it6 = arrayList17.iterator();
            while (it6.hasNext()) {
                View sharedElementFirstOutViews = it6.next();
                Intrinsics.checkNotNullExpressionValue(sharedElementFirstOutViews, "sharedElementFirstOutViews");
                View view18 = sharedElementFirstOutViews;
                Log.v(str6, "View: " + view18 + " Name: " + ViewCompat.getTransitionName(view18));
            }
            Log.v(str6, ">>>>> SharedElementLastInViews <<<<<");
            Iterator<View> it7 = arrayList18.iterator();
            while (it7.hasNext()) {
                View sharedElementLastInViews = it7.next();
                Intrinsics.checkNotNullExpressionValue(sharedElementLastInViews, "sharedElementLastInViews");
                View view19 = sharedElementLastInViews;
                Log.v(str6, "View: " + view19 + " Name: " + ViewCompat.getTransitionName(view19));
            }
        }
        fragmentTransitionImpl.beginDelayedTransition(getContainer(), mergeTransitionsInSequence);
        fragmentTransitionImpl.setNameOverridesReordered(getContainer(), arrayList17, arrayList18, prepareSetNameOverridesReordered, arrayMap6);
        FragmentTransition.setViewVisibility(arrayList20, 0);
        fragmentTransitionImpl.swapSharedElementTargets(obj15, arrayList17, arrayList18);
        return linkedHashMap6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$9(SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2, boolean z, ArrayMap lastInViews) {
        Intrinsics.checkNotNullParameter(lastInViews, "$lastInViews");
        FragmentTransition.callSharedElementStartEnd(operation.getFragment(), operation2.getFragment(), z, lastInViews, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$10(FragmentTransitionImpl impl, View view, Rect lastInEpicenterRect) {
        Intrinsics.checkNotNullParameter(impl, "$impl");
        Intrinsics.checkNotNullParameter(lastInEpicenterRect, "$lastInEpicenterRect");
        impl.getBoundsOnScreen(view, lastInEpicenterRect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$11(ArrayList transitioningViews) {
        Intrinsics.checkNotNullParameter(transitioningViews, "$transitioningViews");
        FragmentTransition.setViewVisibility(transitioningViews, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$14$lambda$13(TransitionInfo transitionInfo, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(transitionInfo, "$transitionInfo");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        transitionInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Transition for operation " + operation + " has completed");
        }
    }

    private final void retainMatchingViews(ArrayMap<String, View> arrayMap, final Collection<String> collection) {
        Set<Map.Entry<String, View>> entries = arrayMap.entrySet();
        Intrinsics.checkNotNullExpressionValue(entries, "entries");
        CollectionsKt.retainAll(entries, new Function1<Map.Entry<String, View>, Boolean>() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$retainMatchingViews$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Map.Entry<String, View> entry) {
                Intrinsics.checkNotNullParameter(entry, "entry");
                return Boolean.valueOf(CollectionsKt.contains(collection, ViewCompat.getTransitionName(entry.getValue())));
            }
        });
    }

    private final void captureTransitioningViews(ArrayList<View> transitioningViews, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                if (transitioningViews.contains(view)) {
                    return;
                }
                transitioningViews.add(view);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = viewGroup.getChildAt(i);
                if (child.getVisibility() == 0) {
                    Intrinsics.checkNotNullExpressionValue(child, "child");
                    captureTransitioningViews(transitioningViews, child);
                }
            }
            return;
        }
        if (transitioningViews.contains(view)) {
            return;
        }
        transitioningViews.add(view);
    }

    private final void findNamedViews(Map<String, View> namedViews, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            namedViews.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = viewGroup.getChildAt(i);
                if (child.getVisibility() == 0) {
                    Intrinsics.checkNotNullExpressionValue(child, "child");
                    findNamedViews(namedViews, child);
                }
            }
        }
    }

    private final void applyContainerChanges(SpecialEffectsController.Operation operation) {
        View view = operation.getFragment().mView;
        SpecialEffectsController.Operation.State finalState = operation.getFinalState();
        Intrinsics.checkNotNullExpressionValue(view, "view");
        finalState.applyState(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0012\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fR\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "signal", "Landroidx/core/os/CancellationSignal;", "(Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/core/os/CancellationSignal;)V", "isVisibilityUnchanged", "", "()Z", "getOperation", "()Landroidx/fragment/app/SpecialEffectsController$Operation;", "getSignal", "()Landroidx/core/os/CancellationSignal;", "completeSpecialEffect", "", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation operation;
        private final CancellationSignal signal;

        public SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal signal) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(signal, "signal");
            this.operation = operation;
            this.signal = signal;
        }

        public final SpecialEffectsController.Operation getOperation() {
            return this.operation;
        }

        public final CancellationSignal getSignal() {
            return this.signal;
        }

        public final boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State.Companion companion = SpecialEffectsController.Operation.State.INSTANCE;
            View view = this.operation.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view, "operation.fragment.mView");
            SpecialEffectsController.Operation.State asOperationState = companion.asOperationState(view);
            SpecialEffectsController.Operation.State finalState = this.operation.getFinalState();
            if (asOperationState != finalState) {
                return (asOperationState == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE) ? false : true;
            }
            return true;
        }

        public final void completeSpecialEffect() {
            this.operation.completeSpecialEffect(this.signal);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000eR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "signal", "Landroidx/core/os/CancellationSignal;", "isPop", "", "(Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/core/os/CancellationSignal;Z)V", "animation", "Landroidx/fragment/app/FragmentAnim$AnimationOrAnimator;", "isAnimLoaded", "getAnimation", "context", "Landroid/content/Context;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator animation;
        private boolean isAnimLoaded;
        private final boolean isPop;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal signal, boolean z) {
            super(operation, signal);
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(signal, "signal");
            this.isPop = z;
        }

        public final FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (this.isAnimLoaded) {
                return this.animation;
            }
            FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.isPop);
            this.animation = loadAnimation;
            this.isAnimLoaded = true;
            return loadAnimation;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0014\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0011H\u0002J\u0006\u0010\u0016\u001a\u00020\u0007R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013¨\u0006\u0017"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "signal", "Landroidx/core/os/CancellationSignal;", "isPop", "", "providesSharedElementTransition", "(Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/core/os/CancellationSignal;ZZ)V", "handlingImpl", "Landroidx/fragment/app/FragmentTransitionImpl;", "getHandlingImpl", "()Landroidx/fragment/app/FragmentTransitionImpl;", "isOverlapAllowed", "()Z", "sharedElementTransition", "", "getSharedElementTransition", "()Ljava/lang/Object;", "transition", "getTransition", "hasSharedElementTransition", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class TransitionInfo extends SpecialEffectsInfo {
        private final boolean isOverlapAllowed;
        private final Object sharedElementTransition;
        private final Object transition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal signal, boolean z, boolean z2) {
            super(operation, signal);
            Object returnTransition;
            boolean z3;
            Object obj;
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(signal, "signal");
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                Fragment fragment = operation.getFragment();
                returnTransition = z ? fragment.getReenterTransition() : fragment.getEnterTransition();
            } else {
                Fragment fragment2 = operation.getFragment();
                returnTransition = z ? fragment2.getReturnTransition() : fragment2.getExitTransition();
            }
            this.transition = returnTransition;
            if (operation.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE) {
                z3 = true;
            } else if (z) {
                z3 = operation.getFragment().getAllowReturnTransitionOverlap();
            } else {
                z3 = operation.getFragment().getAllowEnterTransitionOverlap();
            }
            this.isOverlapAllowed = z3;
            if (!z2) {
                obj = null;
            } else if (z) {
                obj = operation.getFragment().getSharedElementReturnTransition();
            } else {
                obj = operation.getFragment().getSharedElementEnterTransition();
            }
            this.sharedElementTransition = obj;
        }

        public final Object getTransition() {
            return this.transition;
        }

        /* renamed from: isOverlapAllowed, reason: from getter */
        public final boolean getIsOverlapAllowed() {
            return this.isOverlapAllowed;
        }

        public final Object getSharedElementTransition() {
            return this.sharedElementTransition;
        }

        public final boolean hasSharedElementTransition() {
            return this.sharedElementTransition != null;
        }

        public final FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.transition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.sharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl == null ? handlingImpl2 : handlingImpl;
            }
            throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.transition + " which uses a different Transition  type than its shared element transition " + this.sharedElementTransition).toString());
        }

        private final FragmentTransitionImpl getHandlingImpl(Object transition) {
            if (transition == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(transition)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(transition)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + transition + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
