import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ProfileInjectService } from '../profile-inject.service';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css'],
})
export class EditProfileComponent implements OnInit {
  profile = null;
  public formProfile = new FormGroup({
    name: new FormControl(''),
    email: new FormControl(''),
  });
  constructor(
    private profileInject: ProfileInjectService,
    private profileService: ProfileService
  ) {}

  ngOnInit() {
    this.profile = this.profileInject.getProfile();
    this.formProfile.setValue({
      name: this.profile.name,
      email: this.profile.email,
    });
  }

  updateProfile(value) {
    value.phoneNumber = this.profile.phoneNumber;
    this.profileService.updateProfile(value).subscribe((res) => {
      console.log(res);
    });
    console.log(this.profile);
  }
}
