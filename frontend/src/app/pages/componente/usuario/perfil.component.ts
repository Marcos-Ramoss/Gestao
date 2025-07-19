import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { PerfilResponseDto } from '../../../dto/perfil-response.dto';
import { PerfilRequestDto } from '../../../dto/perfil-request.dto';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { NgIf } from '@angular/common';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, ButtonModule, InputTextModule, NgIf, ToastModule],
  providers: [MessageService],
  templateUrl: './perfil.component.html'
})
export class PerfilComponent implements OnInit {
  perfilForm: FormGroup;
  editando = false;
  carregando = true;
  erro: string | null = null;
  sucesso: string | null = null;

  constructor(private fb: FormBuilder, private authService: AuthService, private messageService: MessageService) {
    this.perfilForm = this.fb.group({
      username: [{ value: '', disabled: true }],
      nome: ['', Validators.required],
      cpf: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
    });
  }

  ngOnInit() {
    this.carregarPerfil();
  }

  carregarPerfil() {
    this.carregando = true;
    this.authService.getPerfil().subscribe({
      next: (perfil: PerfilResponseDto) => {
        this.perfilForm.patchValue(perfil);
        this.carregando = false;
        this.erro = null;
      },
      error: () => {
        this.erro = 'Erro ao carregar perfil.';
        this.carregando = false;
      }
    });
  }

  habilitarEdicao() {
    this.editando = true;
    this.perfilForm.get('nome')?.enable();
    this.perfilForm.get('cpf')?.enable();
  }

  salvar() {
    if (this.perfilForm.valid) {
      const data: PerfilRequestDto = {
        nome: this.perfilForm.get('nome')?.value,
        cpf: this.perfilForm.get('cpf')?.value
      };
      this.authService.updatePerfil(data).subscribe({
        next: (perfil: PerfilResponseDto) => {
          this.perfilForm.patchValue(perfil);
          this.editando = false;
          this.sucesso = 'Perfil atualizado com sucesso!';
          this.erro = null;
          this.perfilForm.get('nome')?.disable();
          this.perfilForm.get('cpf')?.disable();
          this.messageService.add({severity:'success', summary:'Sucesso', detail:'Perfil atualizado com sucesso!'});
        },
        error: () => {
          this.erro = 'Erro ao atualizar perfil.';
          this.sucesso = null;
        }
      });
    } else {
      this.perfilForm.markAllAsTouched();
    }
  }
} 