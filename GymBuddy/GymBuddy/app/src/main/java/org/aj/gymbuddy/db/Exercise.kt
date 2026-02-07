package org.aj.gymbuddy.db

import android.net.Uri
import java.util.UUID

enum class ExerciseCategory(
    val id: UUID,
    val title: String
) {

    Warmup(
        id = UUID.randomUUID(),
        title = "Warmup"),
    Back(
        id = UUID.randomUUID(),
        title = "Back"),
    Chest(
        id = UUID.randomUUID(),
        "Chest"),
    Shoulders(
        id = UUID.randomUUID(),
        title = "Shoulders"),
    Wrists(
        id = UUID.randomUUID(),
        title = "Wrists"),
    Legs(
        id = UUID.randomUUID(),
        title = "Legs"),
    Arms(
        id = UUID.randomUUID(),
        title = "Arms"),
    Abs(
        id = UUID.randomUUID(),
        title = "Abs"),
    HIIT(
        id = UUID.randomUUID(),
        title = "HIIT");

    val exercises: List<Exercise> by lazy {
        Exercise.entries.filter { it.category == this }
    }

    enum class Exercise(
        val id: UUID,
        val title: String,
        val demo: Uri,
        val description: String,
        val category: ExerciseCategory
    ) {
        Treadmill(
            id = UUID.randomUUID(),
            title = "Treadmill",
            demo = Uri.parse(""),
            description = """
            • Jog for 5min before workout for warmup
            • This is good for whole body warmup""".trimIndent(),
            category = Warmup
        ),
        Rowing(
            id = UUID.randomUUID(),
            title = "Rowing",
            demo = Uri.parse(""),
            description = """
            • Row for 5min before workout for warmup
            • This warmup is great for back and arms workouts""".trimIndent(),
            category = Warmup
        ),
        Bicycle(
            id = UUID.randomUUID(),
            title = "Bicycle",
            demo = Uri.parse(""),
            description = """
            • Ride bicycle for 5min before workout for warmup
            • This warmup is great for leg workouts""".trimIndent(),
            category = Warmup
        ),
        LatPulldowns(
            id = UUID.randomUUID(),
            title = "Lat pulldowns",
            demo = Uri.parse("S7f0htFwY3M"),
            description = """
            • Do not lean back too much
            • Do not swing, do exercise slowly""".trimIndent(),
            category = Back
        ),
        LatPulldownsMachine(
            id = UUID.randomUUID(),
            title = "Lat pulldowns on machine",
            demo = Uri.parse("t0s3O9vO1Kg"),
            description = """
            • Do not lean back, keep your back straight
            • Make sure you feel that you are training your lats""".trimIndent(),
            category = Back
        ),
        VGripLatPulldowns(
            id = UUID.randomUUID(),
            title = "V-grip lat pulldowns",
            demo = Uri.parse("9hKHZZRL9co"),
            description = """
            • Do not lean back too much
            • Do not swing, exercise slowly""".trimIndent(),
            category = Back
        ),
        SeatedCableRows(
            id = UUID.randomUUID(),
            title = "Seated cable rows",
            demo = Uri.parse("zgqo2NesHZI"),
            description = """
            • Keep your back straight
            • Your elbows must go as close to the body as possible""".trimIndent(),
            category = Back
        ),
        TBarRows(
            id = UUID.randomUUID(),
            title = "T-bar rows",
            demo = Uri.parse("wWGjZ-dO6Co"),
            description = """
            • Do this exercise only if you have some experience
            • Make sure that the weight is going to your chest line
            • Keep your back straight at all time
            • Do not forget to lean forward a bit""".trimIndent(),
            category = Back
        ),
        MachineRows(
            id = UUID.randomUUID(),
            title = "Machine rows",
            demo = Uri.parse("DzCZJ4owKJc"),
            description = """
            • Keep your back straight
            • Do not swing while doing the exercise""".trimIndent(),
            category = Back
        ),
        BackExtensions(
            id = UUID.randomUUID(),
            title = "Back extensions",
            demo = Uri.parse("E2xnYhofT5s"),
            description = """
            • During this exercise do not increase your weight too much and fast
            • Do not bend your back, keep it straight all the time
            • Make sure that you do not over extend your back""".trimIndent(),
            category = Back
        ),
        SupermanIsoHolds(
            id = UUID.randomUUID(),
            title = "Superman iso holds",
            demo = Uri.parse("6KPF6ndeevE"),
            description = """
            • One arm one leg hold for 3sec and both arms both legs hold for 5sec""".trimIndent(),
            category = Back
        ),
        PullUps(
            id = UUID.randomUUID(),
            title = "Pull-ups with a puffed-out chest",
            demo = Uri.parse("Lg-vDV9Nrto"),
            description = """
            • Here make sure that you are targeting the back muscles""".trimIndent(),
            category = Back
        ),
        BenchPress(
            id = UUID.randomUUID(),
            title = "Bench press",
            demo = Uri.parse("WO6b14SwTKo"),
            description = """
            • Lift the barbell over your chest
            • Breath out the air while pushing up the weight""".trimIndent(),
            category = Chest
        ),
        DumbbellBenchPress(
            id = UUID.randomUUID(),
            title = "Dumbbell bench press",
            demo = Uri.parse("OZryqRXhvPQ"),
            description = """
            • Lift the weight over your chest
            • At the bottom your weight should be near your armpits""".trimIndent(),
            category = Chest
        ),
        InclinedDumbbellBenchPress(
            id = UUID.randomUUID(),
            title = "Inclined dumbbell bench press",
            demo = Uri.parse("LEHueYmevcI"),
            description = """
            • At the bottom your weight should be near your armpits
            • Lift the weight over your collarbone (shoulders)""".trimIndent(),
            category = Chest
        ),
        ChestCableFlyes(
            id = UUID.randomUUID(),
            title = "Chest cable flyes",
            demo = Uri.parse("Lx4fmmSqgqw"),
            description = """
            • Make sure your back is straight and you are leaning forward""".trimIndent(),
            category = Chest
        ),
        ChestMachineFlyes(
            id = UUID.randomUUID(),
            title = "Chest machine flyes",
            demo = Uri.parse("fDup2oZ2FB4"),
            description = """
            • Make sure your arms are at the same level with your chest""".trimIndent(),
            category = Chest
        ),
        ChestPressMachine(
            id = UUID.randomUUID(),
            title = "Chest press machine",
            demo = Uri.parse("ncbv5d83Z5o"),
            description = """
            • While lowering the weight go slowly""".trimIndent(),
            category = Chest
        ),
        DumbbellShoulderPress(
            id = UUID.randomUUID(),
            title = "Dumbbell shoulder press",
            demo = Uri.parse("ZQN7M9AxRHE"),
            description = """
            • Go down slowly
            • Keep your arms a bit in front of the shoulder line""".trimIndent(),
            category = Shoulders
        ),
        DumbbellShoulderPressWithRotation(
            id = UUID.randomUUID(),
            title = "Dumbbell shoulder press with rotation",
            demo = Uri.parse("OkikLJ8-LWg"),
            description = """
            • Go down slowly
            • Keep your arms a bit in front of the shoulder line""".trimIndent(),
            category = Shoulders
        ),
        UprightRowsWhide(
            id = UUID.randomUUID(),
            title = "Upright rows (wide)",
            demo = Uri.parse("YL-6Xabt8y0"),
            description = """
            • Go down slower
            • Elbows have to be in line with shoulders at the top
            • While doing the exercise lean forward a bit""".trimIndent(),
            category = Shoulders
        ),
        StandingDumbbellFrontRaises(
            id = UUID.randomUUID(),
            title = "Standing dumbbell front raises",
            demo = Uri.parse("Z_i816UJrr8"),
            description = """
            • Make sure you are leaning to the wall
            • Go down slowly with the weight""".trimIndent(),
            category = Shoulders
        ),
        DumbbellLateralRaises(
            id = UUID.randomUUID(),
            title = "Dumbbell lateral raises",
            demo = Uri.parse("pFVpY-aiRlE"),
            description = """
            • Go down slowly with the weight
            • Lean forward a bit and make sure your back is straight all the time""".trimIndent(),
            category = Shoulders
        ),
        WristCurls(
            id = UUID.randomUUID(),
            title = "Wrist curls",
            demo = Uri.parse("z2ruK2KIkYU"),
            description = """
            • Do not pick too big of a weight because it could just damage your wrists
            • Go slowly while lowering the weight""".trimIndent(),
            category = Wrists
        ),
        ReverseWristCurls(
            id = UUID.randomUUID(),
            title = "Reverse wrist curls",
            demo = Uri.parse("Ly92umsP734"),
            description = """
            • Do not pick too big of a weight because it could just damage your wrists
            • Go slowly while lowering the weight""".trimIndent(),
            category = Wrists
        ),
        RearFrontRotations(
            id = UUID.randomUUID(),
            title = "Rear front rotations",
            demo = Uri.parse("oF2GoPnk0VQ"),
            description = """
            • Do not pick too big of a weight because it could just damage your wrists
            • Go slowly while lowering the weight""".trimIndent(),
            category = Wrists
        ),
        ReversedCurls(
            id = UUID.randomUUID(),
            title = "Reversed curls",
            demo = Uri.parse("29fD7B1FwNA"),
            description = """
            • Do not pick too big of a weight because it could just damage your wrists
            • Go slowly while lowering the weight""".trimIndent(),
            category = Wrists
        ),
        Abductors(
            id = UUID.randomUUID(),
            title = "Abductors",
            demo = Uri.parse("p98d8FYM5Cs"),
            description = """
            • Faster while going out but slower when the machine pushes your legs inside""".trimIndent(),
            category = Legs
        ),
        Adductors(
            id = UUID.randomUUID(),
            title = "Adductors",
            demo = Uri.parse("IeDWFYpmD5s"),
            description = """
            • Fast while pushing your legs together but slow while going out""".trimIndent(),
            category = Legs
        ),
        LegExtensions(
            id = UUID.randomUUID(),
            title = "Leg extensions",
            demo = Uri.parse("6D6-NzPDtfk"),
            description = """
            • While extending go faster but going down must be very slow""".trimIndent(),
            category = Legs
        ),
        StairsCalfRaises(
            id = UUID.randomUUID(),
            title = "Stair’s calf raises",
            demo = Uri.parse("obSIQfdnKoY"),
            description = """
            • Go up a little bit faster but go down slowly""".trimIndent(),
            category = Legs
        ),
        CalfLegPress(
            id = UUID.randomUUID(),
            title = "Calf leg press",
            demo = Uri.parse("pBzuNnTJGJM"),
            description = """
            • Make sure your legs are not fully extended while doing this exercise
            • Do not rush while doing this especially while going down""".trimIndent(),
            category = Legs
        ),
        WalkingLunges(
            id = UUID.randomUUID(),
            title = "Walking lunges",
            demo = Uri.parse("vT7C2eQLdGc"),
            description = """
            • Keep your back straight
            • Don't lunge too far or too short (your knee has to make a 90-degree angle with the ground)""".trimIndent(),
            category = Legs
        ),
        ReversedLunges(
            id = UUID.randomUUID(),
            title = "Reversed lunges",
            demo = Uri.parse("ixYdJ86kKPQ"),
            description = """
            • Keep your back straight
            • Focus on one dot so you won't fall""".trimIndent(),
            category = Legs
        ),
        LegPress(
            id = UUID.randomUUID(),
            title = "Leg press",
            demo = Uri.parse("mVDIcUutr4E"),
            description = """
            • Make sure your butt is not going up from the seat while doing the exercise
            • Breathe out while pushing the weight
            • Your knees and legs have to be more or less in line with the shoulders""".trimIndent(),
            category = Legs
        ),
        LayingLegCurls(
            id = UUID.randomUUID(),
            title = "Laying leg curls",
            demo = Uri.parse("7bFQGhO_mk0"),
            description = """
            • Your knees have to be put not on the machine
            • Curl faster but go back slower
            • Breathe out while curling""".trimIndent(),
            category = Legs
        ),
        SingleLegCurls(
            id = UUID.randomUUID(),
            title = "Single leg curls",
            demo = Uri.parse("9mbvavlp_js"),
            description = """
            • Curl a bit faster but go down slowly
            • Keep your back straight all the time""".trimIndent(),
            category = Legs
        ),
        SeatedLegCurls(
            id = UUID.randomUUID(),
            title = "Seated leg curls",
            demo = Uri.parse("wzdhT7XMfWw"),
            description = """
            • Curl faster but go up slower""".trimIndent(),
            category = Legs
        ),
        BeltSquat(
            id = UUID.randomUUID(),
            title = "Belt squat",
            demo = Uri.parse("oVy_gWykLzw"),
            description = """
            • Make sure your back is straight
            • Breathe out while going up""".trimIndent(),
            category = Legs
        ),
        TricepsCablePushdowns(
            id = UUID.randomUUID(),
            title = "Triceps cable pushdowns",
            demo = Uri.parse("jODU-cMsE74"),
            description = """
            • Make sure you are not using shoulders
            • Fully extend your arms and do not go all the way up
            • Your elbows must stay at the same position while doing the exercise""".trimIndent(),
            category = Arms
        ),
        TricepsCableOverheadExtensions(
            id = UUID.randomUUID(),
            title = "Triceps cable overhead extensions",
            demo = Uri.parse("MsWcPXWBR3I"),
            description = """
            • Keep your back straight and bend your body to the front""".trimIndent(),
            category = Arms
        ),
        BarbellSkullCrushers(
            id = UUID.randomUUID(),
            title = "Barbell skull crushers",
            demo = Uri.parse("ISAS1x8sc5I"),
            description = """
            • When the weight is up make sure that you hold your arms straight at the angle and not just straight above your head
            • Lower down the weight slowly""".trimIndent(),
            category = Arms
        ),
        OverheadSkullCrushers(
            id = UUID.randomUUID(),
            title = "Overhead skull crushers",
            demo = Uri.parse("Mt4e6u1CfSg"),
            description = """
            • When the weight is up make sure that you hold your arms straight at the angle and not just straight above your head
            • Go down slowly and faster when you go up
            • While going up don't forget to blow out the air""".trimIndent(),
            category = Arms
        ),
        LayingTricepsPress(
            id = UUID.randomUUID(),
            title = "Laying triceps press",
            demo = Uri.parse("nTsf-iKRP2g"),
            description = """
            • Make sure your wrists are straight
            • Feel if you are truly lifting with your triceps""".trimIndent(),
            category = Arms
        ),
        TricepsDips(
            id = UUID.randomUUID(),
            title = "Triceps dips",
            demo = Uri.parse("W05VniRAs-Y"),
            description = """
            • Do not lean too much forward because it will more hit your chest not your triceps
            • Your elbows have to be as close as possible to your body""".trimIndent(),
            category = Arms
        ),
        DumbbelSingleArmPreacherCurls(
            id = UUID.randomUUID(),
            title = "Dumbbell single-arm preacher curls",
            demo = Uri.parse("lAOFj02eWw0"),
            description = """
            • Make sure to let the weight down slowly
            • Your back here must be straight (adjust the height of the seat)
            • It is important not to overextend your arms because it can lead to muscle tear""".trimIndent(),
            category = Arms
        ),
        BarbellPreacherCurls(
            id = UUID.randomUUID(),
            title = "Barbell preacher curls",
            demo = Uri.parse("NkPp5GUCkw0"),
            description = """
            • Make sure to let the weight down slowly
            • Your back here must be straight (adjust the height of the seat)
            • It is important not to overextend your arms because it can lead to muscle tear""".trimIndent(),
            category = Arms
        ),
        WideGripBarbellCurls(
            id = UUID.randomUUID(),
            title = "Wide grip barbell curls",
            demo = Uri.parse("DvibTObOwLA"),
            description = """
            • Take your curled barbell wide so you would hold it wider than your shoulders
            • Do not use momentum and do not swing the weight, make sure your back is straight
            • If you want to make sure your back stays straight you can lean to a wall but it limits your exercise motion range
            • If you are not sure about the exercise you should not do it""".trimIndent(),
            category = Arms
        ),
        ConcentrationCurls(
            id = UUID.randomUUID(),
            title = "Concentration curls",
            demo = Uri.parse("M_BM3p3wAlU"),
            description = """
            • Make sure your back is straight at all times
            • The elbow must lean on the knee
            • Recommendation would be not to do this exercise if you are not experienced or if you feel uncomfortable doing it""".trimIndent(),
            category = Arms
        ),
        CableBicepCurls(
            id = UUID.randomUUID(),
            title = "Cable bicep curls",
            demo = Uri.parse("Y44WQ4a226M"),
            description = """
            • Important to keep your back straight it is even good to lean a bit back
            • Do not lift too heavy because it causes you to throw the weight around which does more damage than benefit
            • Slowly let your weight down""".trimIndent(),
            category = Arms
        ),
        SeatedWideBicepCurls(
            id = UUID.randomUUID(),
            title = "Seated wide bicep curls",
            demo = Uri.parse("RPPH6kSLaV0"),
            description = """
            • This exercise targets the inner bicep muscle
            • It is important not to do it too wide and to not sit too straight or too horizontal
            • Slowly let your weights down""".trimIndent(),
            category = Arms
        ),
        SeatedDumbbellCurls(
            id = UUID.randomUUID(),
            title = "Seated dumbbell curls",
            demo = Uri.parse("_6S2E_R8zD8"),
            description = """
            • Make sure your seat is practically vertical
            • Curl your arms with the rotation of wrists as soon as you pass your knees (recommended: one by one arm) and go down slowly""".trimIndent(),
            category = Arms
        ),
        SeatedDumbbellHammerCurls(
            id = UUID.randomUUID(),
            title = "Seated dumbbell hammer curls",
            demo = Uri.parse("zMM_zVTJBlc"),
            description = """
            • Make sure your hands are hanging straight down with dumbbells
            • Curl your arms (recommended: one by one) and don't just drop the weight down but let it down slowly""".trimIndent(),
            category = Arms
        ),
        InclinedCrunches(
            id = UUID.randomUUID(),
            title = "Inclined crunches",
            demo = Uri.parse("VgAlwbUwEtA"),
            description = """
            • It is important not to keep your back straight and try to crunch as much as possible
            • Another key moment is not to go down all the way
            • Try to do this exercise slower for better results
            • If you do it properly you do not need to have any extra weight for this exercise""".trimIndent(),
            category = Abs
        ),
        InclinedReverseCrunches(
            id = UUID.randomUUID(),
            title = "Inclined reverse crunches",
            demo = Uri.parse("EWlJZypLDd0"),
            description = """
            • This exercise targets the lower abs and has many variations
            • Key for doing this exercise is not to use momentum while going up
            • Best approach would be to do it slower""".trimIndent(),
            category = Abs
        ),
        RussianTwists(
            id = UUID.randomUUID(),
            title = "Russian twists",
            demo = Uri.parse("5UriT3Tur4g"),
            description = """
            • Keep your back straight and turn with the whole upper body
            • You don't need to keep your legs in the air, but if you want more effect you should choose the proper weight so you could hold your legs up""".trimIndent(),
            category = Abs
        ),
        HeelTaps(
            id = UUID.randomUUID(),
            title = "Heel taps",
            demo = Uri.parse("F6yk9tHwCeU"),
            description = """
            • Keep your shoulders up from the floor at all times
            • Reach for your heels with the hand and make sure that your heels are not too close or too far""".trimIndent(),
            category = Abs
        ),
        PlankWithMovements(
            id = UUID.randomUUID(),
            title = "Plank with going front and back",
            demo = Uri.parse("kENaEmPUIUA"),
            description = """
            • It is a simple plank just with pushing with your feet forward and then backward
            • This is not as boring as holding a static plank""".trimIndent(),
            category = Abs
        ),
        RollOvers(
            id = UUID.randomUUID(),
            title = "Roll overs",
            demo = Uri.parse("UpJW7YLp3S8"),
            description = """
            • Put your hands on your legs and keep your shoulders up from the floor at all time
            • Now crunch until your hands rolls over your knees
            • In this exercise it is important to crunch""".trimIndent(),
            category = Abs
        ),
        FlutterKicks(
            id = UUID.randomUUID(),
            title = "Flutter kicks",
            demo = Uri.parse("pnbOkOAl97Q"),
            description = """
            • Put your hands under your butt to keep your lower back glued to the floor (it is important so you wouldn't hurt your lower back)
            • Raise your legs (the higher your legs the easier) and hold them straight
            • Now just kick the air with your legs and after that do the crossovers too""".trimIndent(),
            category = Abs
        ),
        BicycleAbs(
            id = UUID.randomUUID(),
            title = "Bicycle",
            demo = Uri.parse("BobbKNHD_pQ"),
            description = """
            • Lock your hands together behind your head and with different elbow try to reach different knee""".trimIndent(),
            category = Abs
        ),
        SpiderManPlankCrunches(
            id = UUID.randomUUID(),
            title = "Spiderman plank crunches",
            demo = Uri.parse("Yf2iA8SXExM"),
            description = """
            • Do the regular plank just instead of holding it pull one your leg to the side and try to reach your elbow with the knee""".trimIndent(),
            category = Abs
        ),
        LegsUpWeightedCrunches(
            id = UUID.randomUUID(),
            title = "Legs up weighted crunches",
            demo = Uri.parse("waiteRXcunk"),
            description = """
            • Keep your legs up they don't need to be fully extended
            • Pick up a weight over your chest and keep your shoulders off the floor at all times
            • Try to reach your toes with the weight in your hands""".trimIndent(),
            category = Abs
        ),
        AbRoller(
            id = UUID.randomUUID(),
            title = "Ab roller",
            demo = Uri.parse("BbdAVTHAw4w"),
            description = """
            • Keep your back a bit arched out (back doesn't have to be straight in the beginning, it straightens up while going down)
            • Do this exercise only if you have stronger lower back, arms, abs, and in general core muscles""".trimIndent(),
            category = Abs
        ),
        TreadmillHIIT(
            id = UUID.randomUUID(),
            title = "Treadmill",
            demo = Uri.parse(""),
            description = """
            • 30sec sprint
            • 20sec walk/jog
            • 15 cycles""".trimIndent(),
            category = HIIT
        ),
        BycicleHIIT(
            id = UUID.randomUUID(),
            title = "Bicycle",
            demo = Uri.parse(""),
            description = """
            • 20sec all out
            • 10sec cruise
            • 24 cycles""".trimIndent(),
            category = HIIT
        ),
        StairsHIIT(
            id = UUID.randomUUID(),
            title = "Stairs",
            demo = Uri.parse(""),
            description = """
            • 12lv single step 1min
            • 15lv double step 2min
            • 5 cycles""".trimIndent(),
            category = HIIT
        ),
        HeavyRopeHIIT(
            id = UUID.randomUUID(),
            title = "Heavy rope",
            demo = Uri.parse(""),
            description = """
            • waves, slams, throws, spirals, whips
            • 30sec intervals
            • 15sec rest between intervals
            • 10 sets""".trimIndent(),
            category = HIIT
        ),
        AnywhereHIIT(
            id = UUID.randomUUID(),
            title = "Anywhere",
            demo = Uri.parse(""),
            description = """
            • 30sec sprint
            • 15 burpees
            • 30 high knees
            • 25sec rest
            • 10 cycles""".trimIndent(),
            category = HIIT
        ),
        ParkBenchHIIT(
            id = UUID.randomUUID(),
            title = "Park bench",
            demo = Uri.parse(""),
            description = """
            • 30sec sprint
            • 15 single leg step-ups / 15 squat jumps
            • 15 tricep dips
            • 45sec rest
            • 10 cycles""".trimIndent(),
            category = HIIT
        ),
        IndoorHIIT(
            id = UUID.randomUUID(),
            title = "Indoor",
            demo = Uri.parse(""),
            description = """
            • 15 lunge jumps / 15 push-ups
            • 25 squats
            • 20 chair dips
            • 40 mountain climbers
            • 35sec rest
            • 10 cycles""".trimIndent(),
            category = HIIT
        ),
        Track(
            id = UUID.randomUUID(),
            title = "Track",
            demo = Uri.parse(""),
            description = """
            • 2x sprint 100m and jog the rest of the lap
            • 2x sprint 200m and jog the rest of the lab
            • 2x side shuffle 100m facing each side
            • sprint 100m and jog the rest of the lap
            • jog 1 lap
            • 1 cycle""".trimIndent(),
            category = HIIT
        );

        override fun toString() = title
    }
}
