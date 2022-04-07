import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ProfileInjectService {
  private profile = null;
  constructor() {}
  setProfile(customer) {
    this.profile = customer;
  }
  getProfile() {
    return this.profile;
  }
}
