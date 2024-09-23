### Project start
- Ordering parts and creation of many raw sketches on paper
- I ordered the wrong motors ([[JGA25-370]] instead of [[JGA25-370 with Encoder]]) so I have to add [[LM393 IR Speed Sensor]]s to be able to control the motor speed properly

### Wheel prototyping
- Selected materials are [[PETG]] for the wheel core and [[TPU]] for the flexible part
- Based the overall size on motor dimensions
- Testing how strong the printed prototype is -> no flex no problems
- I noticed that the spacing for the speed encoder was not correct -> 1mm adjustment to add space for the walls of the sensor enclosure

### Basic research
- Early basic research to avoid frying all the electronics ...
	- No fires and no big surprises but a lot of fun to test out all the planned concepts like buck converter
- I noticed that my micro controller [[Arduino Nano V3]] has not enough ports for all six servos and other planned features;
	- So I will use a [[PCA9685PW 16 Channel 12-Bit PWM Servo Shield Driver]] with [[I2C Interface]]
- A [good friend (K0rmarun)](https://github.com/k0rmarun) also pointed out than I should handle the motor control directly with a micro controller instead of indirect control via the [[Raspberry PI 4B]] to keep the processing in a realtime manner -> I will follow this advice and only give the [[Arduino Nano V3]] basic commands from the [[Raspberry PI 4B]] like "move in this direction with this speed" and then handle the detailed processing on the micro controller. But I also want to plot this data to [[Grafana]] so maybe there will be at least some feedback back to the [[Raspberry PI 4B]]

![](/Images/Week2.jpg)

### Wheel Mount Structure
- The design is more difficult than expected since the sensor mount adds a lot 3d complexity and Im still a beginner in [[Fusion 360]]
  
...
  

