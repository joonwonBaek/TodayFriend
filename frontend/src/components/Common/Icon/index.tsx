import { ForwardedRef, forwardRef, HTMLAttributes } from 'react';

import { StyledIconContainer } from './style';

interface IconProps extends HTMLAttributes<HTMLSpanElement> {
  name: string;
  isFill?: boolean;
  className?: string;
}

const Icon = forwardRef(
  (
    { name, isFill = true, className = '', ...props }: IconProps,
    ref: ForwardedRef<HTMLSpanElement>,
  ) => {
    return (
      <StyledIconContainer
        {...props}
        ref={ref}
        className={
          isFill
            ? `material-icons ${className}`
            : `material-symbols-outlined  ${className}`
        }>
        {name}
      </StyledIconContainer>
    );
  },
);

Icon.displayName = 'Icon';

export default Icon;
