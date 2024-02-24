import { SubmitHandler, useForm } from 'react-hook-form';

import { HookFormInputListProps } from '@/types/input';

import LabelInput from '../Common/Input/LabelInput';
import { FormWrapper, InputList, InputListWrapper } from './style';

interface SignUpFormData {
  fullName: string;
  email: string;
  password: string;
  passwordCheck: string;
}

export const SignUpForm = () => {
  const { register, handleSubmit, formState } = useForm<SignUpFormData>({
    mode: 'onChange',
  });

  const { errors } = formState;

  const onSubmit: SubmitHandler<SignUpFormData> = (data) => console.log(data);

  const inputList: HookFormInputListProps<SignUpFormData> = [
    {
      label: '이름',
      name: 'fullName',
      required: true,
      placeholder: '이름을 입력해 주세요.',
    },
    {
      label: '이메일',
      name: 'email',
      required: true,
      placeholder: '이메일을 입력해 주세요.',
      type: 'email',
    },
    {
      label: '비밀번호',
      name: 'password',
      required: true,
      placeholder: '비밀번호를 입력해 주세요.',
      type: 'password',
    },
    {
      label: '비밀번호 확인',
      name: 'passwordCheck',
      required: true,
      placeholder: '비밀번호를 다시 입력해 주세요.',
      type: 'password',
    },
  ];

  return (
    <FormWrapper onSubmit={handleSubmit(onSubmit)}>
      <InputListWrapper>
        {inputList.map((props) => (
          <InputList key={props.name}>
            <LabelInput
              register={register}
              errors={errors}
              formState={formState}
              {...props}
            />
          </InputList>
        ))}
      </InputListWrapper>
      <button type="submit">회원가입</button>
    </FormWrapper>
  );
};
