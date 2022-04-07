import { Component, OnInit } from '@angular/core';
import { ProfileInjectService } from '../profile-inject.service';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  profile = null;
  constructor(
    private profileService: ProfileService,
    private profileServiceInject: ProfileInjectService
  ) {}

  ngOnInit() {
    this.profileService
      .getProfile(this.profileServiceInject.getProfile().phoneNumber)
      .subscribe((res) => {
        this.profile = res;
      });
  }
}
