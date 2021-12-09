package com.example.quickworkout

import com.example.quickworkout.model.ExerciseModel

class Constants {
    companion object {
        fun defaultExerciseList(): ArrayList<ExerciseModel> {
            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks =
                ExerciseModel(1,
                    "Jumping Jacks",
                    R.drawable.ic_jumping_jacks,
                    false,
                    false)

            exerciseList.add(jumpingJacks)

            val wallSits =
                ExerciseModel(2,
                    "Wall Sits",
                    R.drawable.ic_wall_sit,
                    false,
                    false)

            exerciseList.add(wallSits)

            val pushUp =
                ExerciseModel(3,
                    "Push Up",
                    R.drawable.ic_push_up,
                    false,
                    false)

            exerciseList.add(pushUp)

            val abdominalCrunch =
                ExerciseModel(4,
                    "Abdominal Crunch",
                    R.drawable.ic_abdominal_crunch,
                    false,
                    false)

            exerciseList.add(abdominalCrunch)

            val stepUpOnChair =
                ExerciseModel(5,
                    "Step up On Chair",
                    R.drawable.ic_step_up_onto_chair,
                    false,
                    false)

            exerciseList.add(stepUpOnChair)

            val squats =
                ExerciseModel(6,
                    "Squats",
                    R.drawable.ic_squat,
                    false,
                    false)

            exerciseList.add(squats)

            val tricepsDipOnChair =
                ExerciseModel(7,
                    "Triceps Dip on Chair",
                    R.drawable.ic_triceps_dip_on_chair,
                    false,
                    false)

            exerciseList.add(tricepsDipOnChair)

            val planks =
                ExerciseModel(8,
                    "Planks",
                    R.drawable.ic_plank,
                    false,
                    false)

            exerciseList.add(planks)

            val highKneesRunningInPlace =
                ExerciseModel(9,
                    "High Knees Running In Place",
                    R.drawable.ic_high_knees_running_in_place,
                    false,
                    false)

            exerciseList.add(highKneesRunningInPlace)

            val lunges =
                ExerciseModel(10,
                    "Lunges",
                    R.drawable.ic_lunge,
                    false,
                    false)

            exerciseList.add(lunges)

            val pushUpAndRotation =
                ExerciseModel(11,
                    "PushUp and Rotations",
                    R.drawable.ic_push_up_and_rotation,
                    false,
                    false)

            exerciseList.add(pushUpAndRotation)

            val sidePlank =
                ExerciseModel(12,
                    "Side Plank",
                    R.drawable.ic_side_plank,
                    false,
                    false)

            exerciseList.add(sidePlank)

            return exerciseList
        }
    }
}