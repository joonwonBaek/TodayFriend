import {
  FieldErrors,
  FieldValues,
  FormState,
  Path,
  RegisterOptions,
  UseFormRegister,
} from 'react-hook-form';

export interface HookFormInputProps<T extends FieldValues>
  extends React.ComponentProps<'input'> {
  name: Path<T>;
  register: UseFormRegister<T>;
  label?: string;
  type?: 'email' | 'password' | 'text';
  required?: boolean;
  formState?: FormState<T>;
  errors?: FieldErrors<T>;
  validation?: RegisterOptions;
  isErrorCheck?: boolean;
}

export type HookFormInputListProps<T extends FieldValues> =
  HookFormInputListType<T>[];

export interface HookFormInputListType<T extends FieldValues>
  extends Omit<HookFormInputProps<T>, 'register' | 'errors'> {
  guide?: React.ReactNode;
}
