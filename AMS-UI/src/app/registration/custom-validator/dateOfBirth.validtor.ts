import { AbstractControl } from '@angular/forms';

export function   validateDateOfBirth(control: AbstractControl) : { [key: string]: boolean } | null {
    const today = new Date();
    const dob = new Date(control.get('dateOfBirth')?.value);
    if(today < dob) {
      return { invalidDateOfBirth: true };
    }
    const age = today.getFullYear() - dob.getFullYear();
    const monthDiff = today.getMonth() - dob.getMonth();
    const dayDiff = today.getDate() - dob.getDate();

    const isUnder18 = age < 18 || (age === 18 && (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)));
     if (isUnder18) {
      return { underAge: true };
    }
    return null;
  }