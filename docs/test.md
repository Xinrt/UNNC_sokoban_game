[TOC]

## Junit Tests

#### 1. Class: GameObject

##### Method: fromChar(char c)

| Test | Inputs | Input Type | Expected Outcome            | Test Outcome                | Status | Time                          |
| :--: | ------ | ---------- | --------------------------- | --------------------------- | ------ | ----------------------------- |
|  1   | 'w'    | char       | GameObject.WALL             | GameObject.WALL             | Pass   | Normal test & Regression test |
|  2   | ' '    | char       | GameObject.FLOOR            | GameObject.FLOOR            | Pass   | Normal test & Regression test |
|  3   | 'c'    | char       | GameObject.CRATE            | GameObject.CRATE            | Pass   | Normal test & Regression test |
|  4   | 'd'    | char       | GameObject.DIAMOND          | GameObject.DIAMOND          | Pass   | Normal test & Regression test |
|  5   | 's'    | char       | GameObject.KEEPER           | GameObject.KEEPER           | Pass   | Normal test & Regression test |
|  6   | 'h'    | char       | GameObject.KEEPERRED        | GameObject.KEEPERRED        | Pass   | Normal test & Regression test |
|  7   | 'm'    | char       | GameObject.DOOR             | GameObject.DOOR             | Pass   | Normal test & Regression test |
|  8   | 'i'    | char       | GameObject.ICE              | GameObject.ICE              | Pass   | Normal test & Regression test |
|  9   | 'o'    | char       | GameObject.CRATE_ON_DIAMOND | GameObject.CRATE_ON_DIAMOND | Pass   | Normal test & Regression test |
|  10  | '='    | char       | GameObject.DEBUG_OBJECT     | GameObject.DEBUG_OBJECT     | Pass   | Normal test & Regression test |



##### Method: getCharSymbol()

| Test | Inputs                      | Input Type | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | --------------------------- | ---------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | GameObject.WALL             | GameObject | 'W'              | 'W'          | Pass   | Normal test & Regression test |
| 2    | GameObject.FLOOR            | GameObject | ' '              | ' '          | Pass   | Normal test & Regression test |
| 3    | GameObject.CRARE            | GameObject | 'C'              | 'C'          | Pass   | Normal test & Regression test |
| 4    | GameObject.DIAMOND          | GameObject | 'D'              | 'D'          | Pass   | Normal test & Regression test |
| 5    | GameObject.KEEPER           | GameObject | 'S'              | 'S'          | Pass   | Normal test & Regression test |
| 6    | GameObject.KEEPERRED        | GameObject | 'H'              | 'H'"         | Pass   | Normal test & Regression test |
| 7    | GameObject.DOOR             | GameObject | 'M'              | 'M'          | Pass   | Normal test & Regression test |
| 8    | GameObject.ICE              | GameObject | 'I'              | 'I'          | Pass   | Normal test & Regression test |
| 9    | GameObject.CRATE_ON_DIAMOND | GameObject | 'O'              | 'O'          | Pass   | Normal test & Regression test |
| 10   | GameObject.DEBUG_OBJECT     | GameObject | '='              | '='          | Pass   | Normal test & Regression test |



#### 2. Class: GameEngine

##### Method: isDebugActive()

| Test | Inputs                     | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | -------------------------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | GameEngine.isDebugActive() | false            | false        | Pass   | Normal test & Regression test |



##### Method: handleKey(KeyCode code)

| Test | Inputs       | Expected Outcome   | Test Outcome       | Status | Time                          |
| ---- | ------------ | ------------------ | ------------------ | ------ | ----------------------------- |
| 1    | KeyCode.LEFT | keeperPosition.y-1 | keeperPosition.y-1 | Pass   | Normal test & Regression test |



##### Method: findPosition()

| Test | Inputs | Expected Outcome | Test Outcome  | Status | Time                          |
| ---- | ------ | ---------------- | ------------- | ------ | ----------------------------- |
| 1    | null   | Point(18, 10)    | Point(18, 10) | Pass   | Normal test & Regression test |



##### Method: isGameComplete()

| Test | Inputs | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | ------ | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | null   | false            | false        | Pass   | Normal test & Regression test |



##### Method: getNextLevel()

| Test | Inputs | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | ------ | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | null   | 1                | 1            | Pass   | Normal test & Regression test |



##### Method: getCurrentLevel()

| Test | Inputs | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | ------ | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | null   | 1                | 1            | Pass   | Normal test & Regression test |



#### 3. Class: GameGrid

##### Method: translatePoint(Point sourceLocation, Point delta)

| Test | Inputs                                              | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | --------------------------------------------------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | source position: Point(0,2)<br />delta: Point(0, 4) | Point(0,6)       | Point(0,6)   | Pass   | Normal test & Regression test |



##### Method: getTargetFromSource(Point source, Point delta)

| Test | Inputs                                              | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | --------------------------------------------------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | source position: Point(0,1)<br />delta: Point(0, 2) | null             | null         | Pass   | Normal test & Regression test |



##### Method: getGameObjectAt(int col, int row)

| Test | Inputs               | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | -------------------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | col = 2<br />row = 2 | null             | null         | Pass   | Normal test & Regression test |



##### Method: getGameObjectAt(Point p)

| Test | Inputs     | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | ---------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | Point(1,1) | null             | null         | Pass   | Normal test & Regression test |



##### Method: removeGameObjectAt(Point position)

| Test | Inputs     | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | ---------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | Point(1,1) | true             | true         | Pass   | Normal test & Regression test |



##### method: putGameObjectAt(GameObject gameObject, int x, int y)

| Test | Inputs                                 | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | -------------------------------------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | GameObject.CRATE<br />x = 2<br />y = 2 | true             | true         | Pass   | Normal test & Regression test |



##### method: putGameObjectAt(GameObject gameObject, Point p)

| Test | Inputs                           | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | -------------------------------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | GameObject.CRATE<br />Point(2,2) | true             | true         | Pass   | Normal test & Regression test |



##### method: toString()

| Test | Inputs | Expected Outcome                      | Test Outcome                          | Status | Time                          |
| ---- | ------ | ------------------------------------- | ------------------------------------- | ------ | ----------------------------- |
| 1    | null   | "=====\n=====\n=====\n=====\n=====\n" | "=====\n=====\n=====\n=====\n=====\n" | Pass   | Normal test & Regression test |



#### 4. Class: GameLogger

##### Method: info(String message)

| Test | Inputs                     | Expected Outcome                                             | Test Outcome                                                 | Status | Time                          |
| ---- | -------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------ | ----------------------------- |
| 1    | "This is logger info test" | main.com.ae2dms.util.GameLogger info<br />-- This is logger info test | main.com.ae2dms.util.GameLogger info<br />-- This is logger info test | Pass   | Normal test & Regression test |



##### Method: warning()

| Test | Inputs                        | Expected Outcome                                             | Test Outcome                                                 | Status | Time                          |
| ---- | ----------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------ | ----------------------------- |
| 1    | "This is logger warning test" | main.com.ae2dms.util.GameLogger warning<br />-- This is logger warning test | main.com.ae2dms.util.GameLogger warning<br />-- This is logger warning test | Pass   | Normal test & Regression test |



##### Method: severe()

| Test | Inputs                       | Expected Outcome                                             | Test Outcome                                                 | Status | Time                          |
| ---- | ---------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------ | ----------------------------- |
| 1    | "This is logger server test" | main.com.ae2dms.util.GameLogger warning<br />-- This is logger server test | main.com.ae2dms.util.GameLogger warning<br />-- This is logger server test | Pass   | Normal test & Regression test |



#### 5. Class: Level

##### Method: isComplete()

| Test | Inputs        | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | ------------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | current level | true             | true         | Pass   | Normal test & Regression test |



##### Method: getName()

| Test | Inputs        | Expected Outcome | Test Outcome   | Status | Time                          |
| ---- | ------------- | ---------------- | -------------- | ------ | ----------------------------- |
| 1    | current level | "Second level"   | "Second level" | Pass   | Normal test & Regression test |



##### Method: getIndex()

| Test | Inputs        | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | ------------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | current level | 2                | 2            | Pass   | Normal test & Regression test |



##### Method: getKeeperPosition()

| Test | Inputs        | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | ------------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | current level | Point(0,3)       | Point(0,3)   | Pass   | Normal test & Regression test |



##### Method: getTargetObject(Point source, Point delta)

| Test | Inputs        | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | ------------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | current level | Point(0,3)       | Point(0,3)   | Pass   | Normal test & Regression test |



##### Method: toString()

| Test | Inputs        | Expected Outcome | Test Outcome    | Status | Time                          |
| ---- | ------------- | ---------------- | --------------- | ------ | ----------------------------- |
| 1    | current level | "WIWSW WWWWW\n"  | "WIWSW WWWWW\n" | Pass   | Normal test & Regression test |



#### 6. Class: Main  - JavaFX Tests

##### Funtion: view inforamtion

| Test | Inputs                    | Expected Outcome            | Test Outcome                | Status | Time                          |
| ---- | ------------------------- | --------------------------- | --------------------------- | ------ | ----------------------------- |
| 1    | `clickOn("#Information")` | open the information screen | open the information screen | Pass   | Normal test & Regression test |



##### Function: scroll the bar

| Test | Inputs                           | Expected Outcome    | Test Outcome        | Status | Time                          |
| ---- | -------------------------------- | ------------------- | ------------------- | ------ | ----------------------------- |
| 1    | `scroll(VerticalDirection.DOWN)` | Scroll the bar down | Scroll the bar down | Pass   | Normal test & Regression test |



##### Function: click back button on the information screen

| Test | Inputs             | Expected Outcome     | Test Outcome         | Status | Time                          |
| ---- | ------------------ | -------------------- | -------------------- | ------ | ----------------------------- |
| 1    | `clickOn("#back")` | back to start screen | back to start screen | Pass   | Normal test & Regression test |



##### Function: click the start button

| Test | Inputs                  | Expected Outcome             | Test Outcome                 | Status | Time                          |
| ---- | ----------------------- | ---------------------------- | ---------------------------- | ------ | ----------------------------- |
| 1    | `clickOn("#startGame")` | open the game playing screen | open the game playing screen | Pass   | Normal test & Regression test |



##### Function: press button to control blue keeper

| Test | Inputs               | Expected Outcome       | Test Outcome           | Status | Time                          |
| ---- | -------------------- | ---------------------- | ---------------------- | ------ | ----------------------------- |
| 1    | `push(KeyCode.LEFT)` | blue keeper moves left | blue keeper moves left | Pass   | Normal test & Regression test |



##### Function: press keyboard letter "L" to use the bomb of blue keeper

| Test | Inputs            | Expected Outcome                                             | Test Outcome                                                 | Status | Time                          |
| ---- | ----------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------ | ----------------------------- |
| 1    | `push(KeyCode.L)` | objects at up, down, left and right directions of blue keeper are blown up | objects at up, down, left and right directions of blue keeper are blown up | Pass   | Normal test & Regression test |



##### Function: press keyboard letter "F" to use the bomb of red keeper

| Test | Inputs            | Expected Outcome                                             | Test Outcome                                                 | Status | Time                          |
| ---- | ----------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------ | ----------------------------- |
| 1    | `push(KeyCode.F)` | objects at up, down, left and right directions of red keeper are blown up | objects at up, down, left and right directions of red keeper are blown up | Pass   | Normal test & Regression test |



##### Function: press button to control red keeper

| Test | Inputs            | Expected Outcome       | Test Outcome           | Status | Time                          |
| ---- | ----------------- | ---------------------- | ---------------------- | ------ | ----------------------------- |
| 1    | `push(KeyCode.D)` | red keeper moves right | red keeper moves right | Pass   | Normal test & Regression test |

 

##### Function: use shortcut key to realize menu functions

| Test | Inputs                              | Expected Outcome | Test Outcome | Status | Time                          |
| ---- | ----------------------------------- | ---------------- | ------------ | ------ | ----------------------------- |
| 1    | `push(KeyCode.SHORTCUT, KeyCode.Z)` | undo             | undo         | Pass   | Normal test & Regression test |



##### Function: use mouse click to realize menu functions

| Test | Inputs                                               | Expected Outcome    | Test Outcome        | Status | Time                          |
| ---- | ---------------------------------------------------- | ------------------- | ------------------- | ------ | ----------------------------- |
| 1    | `clickOn("#Level"))`<br />`clickOn("#resetLevelId")` | reset current level | reset current level | Pass   | Normal test & Regression test |

