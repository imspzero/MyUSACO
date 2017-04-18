CXXFLAGS =	-O2 -g -Wall -fmessage-length=0


TARGET =	ride combo wormhole skidesign preface subset runround lamps

ride:	
	$(CXX)	Section1.1/ride.cpp -o ../Debug/ride

combo:	
	$(CXX)	Section1.3/combo.cpp -o ../Debug/combo

wormhole:	
	$(CXX)	Section1.3/wormhole.cpp -o ../Debug/wormhole

skidesign:	
	$(CXX)	Section1.3/skidesign.cpp -o ../Debug/skidesign

preface:	
	$(CXX)	Section2.2/preface.cpp -o ../Debug/preface
	
runround:	
	$(CXX)	Section2.2/runround.cpp -o ../Debug/runround

lamps:	
	$(CXX)	Section2.2/lamps.cpp -o ../Debug/lamps

subset:	
	$(CXX)	Section2.2/subset.cpp -o ../Debug/subset

all:	$(TARGET)

clean:
	rm -f $(TARGET)
