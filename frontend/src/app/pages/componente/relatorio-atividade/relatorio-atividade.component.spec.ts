import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatorioAtividadeComponent } from './relatorio-atividade.component';

describe('RelatorioAtividadeComponent', () => {
  let component: RelatorioAtividadeComponent;
  let fixture: ComponentFixture<RelatorioAtividadeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RelatorioAtividadeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatorioAtividadeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
