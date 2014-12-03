#ifndef __HELLOWORLDSERVICE_HH__
#define __HELLOWORLDSERVICE_HH__

#include "IHelloWorldService.hh"

using namespace android;

class HelloWorldService : public BnHelloWorldService {
public:
  HelloWorldService();
  virtual ~HelloWorldService();

  static void instantiate();

  virtual void hello();
};

#endif
