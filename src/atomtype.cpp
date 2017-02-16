4/**
 * @file atomtype.cpp
 * @author Charlène Servantie
 * M1-ALMA 2017    
 */
#include "atomtype.hpp"
#include <string>

class AtomType {
    private:

    public:	
};

class Terminal : public AtomType {
private:
	string terminal;
public: 
};

class NonTerminal : public AtomType {
private:
  std:vector<AtomType> atoms;
  
};
