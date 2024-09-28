
# Week 1
### Project start
- Ordering parts and creation of many raw sketches on paper
- I ordered the wrong motors ([[JGA25-370]] instead of [[JGA25-370 with Encoder]]) so I have to add [[LM393 IR Speed Sensor]]s to be able to control the motor speed properly

### Wheel prototyping
- Selected materials are [[PETG]] for the wheel core and [[TPU]] for the flexible part
- Based the overall size on motor dimensions
- Testing how strong the printed [[PETG]] prototype is → no flex no problems
- I noticed that the spacing for the speed encoder was not correct → 1 mm adjustment to add space for the walls of the sensor enclosure

# Week 2

### Basic research
- Early basic research to avoid frying all the electronics ...
	- No fires and no big surprises but a lot of fun to test out all the planned concepts like buck converter
- I noticed that my microcontroller [[Arduino Nano V3]] has not enough ports for all six servos and other planned features;
	- So I will use a [[PCA9685PW 16 Channel 12-Bit PWM Servo Shield Driver]] with [[I2C Interface]]
- A [good friend (K0rmarun)](https://github.com/k0rmarun) also pointed out that I should handle the motor control directly with a microcontroller instead of indirect control via the [[Raspberry PI 4B]] to keep the processing in a realtime manner -> I will follow this advice and only give the [[Arduino Nano V3]] basic commands from the [[Raspberry PI 4B]] like "move in this direction with this speed" and then handle the detailed processing on the micro controller. But I also want to plot this data to [[Grafana]] so maybe there will be at least some feedback back to the [[Raspberry PI 4B]]

![](../Images/Week2.jpg)

### Wheel mount structure
- The design is more difficult than expected since the sensor mount adds a lot of 3d complexities, and I'm still a beginner in [[Fusion 360]]

# Week 3

### Print of [[TPU]] wheel parts 

After some more tests, I have decided to go forward with the current design.
Sure, there are still some improvements possible in regard to material flex and performance on flat surfaces,
but it should be good enough for my use cases.

![](../Images/Week3_Rover_TPU_Print.jpg)

### Wheel Mount Structure
I have moved my workflow back to paper sketches to solve some persisting design problems.


### Motor Driver

I have learned that [[TB6612FNG]] motor drivers could be a better choice than the [[L298N]] motor driver.
[[TB6612FNG]] is way smaller and produces less heat, which also means a much higher efficiency and battery lifetime.

https://www.youtube.com/watch?v=JPPTRj0KWbg

I will stick to the [[L298N]] for now since I already own them, but I might switch them out for the final design or
at least create a modular design to replace them later on.

I also learned (but not tested yet) that the [[L298N]] leads to a major voltage drop which might require changing
the buck converter output from 6 V to around 7 V.

### Motor Control

For the motor control I plan to write a Proportional–integral–derivative controller to make sure that the individual motors perform as expected.

https://en.wikipedia.org/wiki/Proportional%E2%80%93integral%E2%80%93derivative_controller

...
  

