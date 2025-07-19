import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { NgIf } from '@angular/common';
import { AuthService } from '../../../services/auth.service';
import { AuthRequestDto } from '../../../dto/auth-request.dto';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  standalone: true,
  providers: [MessageService],
  imports: [ReactiveFormsModule, ButtonModule, InputTextModule, NgIf, ToastModule]
})
export class RegisterFormComponent {
  registerForm: FormGroup;
  isRegistering = false;
  registerSuccess = false;
  registerError: string | null = null;

  constructor(private fb: FormBuilder, private authService: AuthService, private messageService: MessageService) {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      nome: ['', Validators.required],
      cpf: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.isRegistering = true;
      this.registerSuccess = false;
      this.registerError = null;
      const data: AuthRequestDto = this.registerForm.value;
      this.authService.register(data).subscribe({
        next: () => {
          this.isRegistering = false;
          this.registerSuccess = true;
          this.registerForm.reset();
          this.messageService.add({severity:'success', summary:'Sucesso', detail:'Usuário cadastrado com sucesso!'});
        },
        error: (err) => {
          this.isRegistering = false;
          this.registerError = 'Erro ao cadastrar usuário.';
        }
      });
    } else {
      this.registerForm.markAllAsTouched();
    }
  }
} 