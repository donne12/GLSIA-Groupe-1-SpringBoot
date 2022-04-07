import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ProfileInjectService } from '../profile-inject.service';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  message = '';
  credentials = new FormGroup({
    userId: new FormControl(''),
    password: new FormControl(''),
  });
  constructor(
    private profileService: ProfileService,
    private profileInject: ProfileInjectService,
    private router: Router
  ) {}

  ngOnInit() {}

  login(credentials) {
    this.profileService.getProfile(credentials.userId).subscribe((res) => {
      if (res.status != 200) {
        this.profileInject.setProfile(res);
        this.router.navigate(['/home']);
      }
      this.message = 'Numéro de téléphone ou mot de passe invalide.';
    });
  }
}
