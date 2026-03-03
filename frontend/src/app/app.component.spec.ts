import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

describe('AppComponent', () => {
  beforeEach(async () => {
    localStorage.clear();
    await TestBed.configureTestingModule({
      imports: [AppComponent],
      providers: [provideHttpClient(), provideHttpClientTesting()],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('should render the Todo List heading', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Todo List');
  });

  it('should start in light mode by default', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    expect(fixture.componentInstance.darkMode).toBeFalse();
    const container = fixture.nativeElement.querySelector('.container');
    expect(container.classList.contains('dark')).toBeFalse();
  });

  it('should toggle dark mode on button click', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const button = fixture.nativeElement.querySelector('.theme-toggle');
    button.click();
    fixture.detectChanges();
    expect(fixture.componentInstance.darkMode).toBeTrue();
    const container = fixture.nativeElement.querySelector('.container');
    expect(container.classList.contains('dark')).toBeTrue();
  });

  it('should persist dark mode preference in localStorage', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    fixture.componentInstance.toggleDarkMode();
    expect(localStorage.getItem('darkMode')).toBe('true');
    fixture.componentInstance.toggleDarkMode();
    expect(localStorage.getItem('darkMode')).toBe('false');
  });

  it('should restore dark mode from localStorage', () => {
    localStorage.setItem('darkMode', 'true');
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    expect(fixture.componentInstance.darkMode).toBeTrue();
  });
});
