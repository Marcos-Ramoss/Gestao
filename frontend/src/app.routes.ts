import { Routes } from '@angular/router';
import { HomeComponent } from './app/pages/home/home.component';
import { LoginComponent } from './app/pages/auth/login.component';
import { AuthGuard } from './app/core/auth.guard';
import { RelatorioAtividadeComponent } from './app/pages/componente/relatorio-atividade/relatorio-atividade.component';

export const appRoutes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'app', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/usuarios', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/perfil', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/faturamento', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/recursos', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/recursos/novo', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/recursos/:id/editar', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/feriados', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/feriados/novo', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/feriados/:id/editar', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/areas', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/areas/novo', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/areas/:id/editar', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/contratos', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/contratos/novo', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/contratos/:codigoContrato/editar', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/ordens-servico', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'app/relatorio-atividade', component: RelatorioAtividadeComponent, canActivate: [AuthGuard] },
];
