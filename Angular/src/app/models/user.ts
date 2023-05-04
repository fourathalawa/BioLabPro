export class User {
  id?: string;
  userFirstName?: string;
  userLastName?: string;
  tel?: string;
  email?: string;
  isBanned?: boolean;
  adress?:string;
  image?:string;
  authorities?: string[];
  Banned?: boolean;
  userPassword?: string;

  constructor(
    id?: any,
    userFirstName?: any,
    userLastName?: string,
    userPassword?: string,
    tel?: string,
    email?: string,
    isBanned?: number,
    adress?:string,
    image?:any,
    authorities?: string[],
  ) {}
}
