

class PetersonAlgorithm implements Lock {
  boolean wantCS[] = {false, false};
  int[] turn = {0, 0};
  public void requestCS(int i) {
    int j = 1 - i;
    wantCS[i] = true;
    turn[i] = turn[j] + 1;
    while(wantCS[j] && ((turn[i] == turn[j])||(turn[i] > turn[j]))) {
      if(turn[i] == turn[j]) {
        turn[i] = turn[j] + 1;
      }
    }
  }

  public void releaseCS(int i) {
    wantCS[i] = false;
    turn[i] = 0;
  }

  // without the choosing variable
  public void requestCS(int i) {
    for(int k = 0; k < N; k++) {
      if(number[k] > number[i]) {
        number[i] = number[k];
      }
    }
    number[i]++;

    for(int k=0; k < N; k++) {
      while((number[k] != 0)&&((number[k] < number[i])||((number[k] < number[i])||((number[k]==number[i]) && k<i)))){}
      }
  }
}
