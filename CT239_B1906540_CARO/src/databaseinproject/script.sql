USE [AccountLogin]
GO
/****** Object:  Table [dbo].[ACCOUNT]    Script Date: 11/28/2022 11:31:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ACCOUNT](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nchar](10) NULL,
	[pass] [nchar](10) NULL,
	[avatar] [nchar](10) NULL,
	[numberOfGame] [int] NULL,
	[numberOfWin] [int] NULL,
	[numberOfDraw] [int] NULL,
	[IsOnline] [int] NULL,
	[IsPlaying] [int] NULL,
 CONSTRAINT [PK_ACCOUNT] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[ACCOUNT] ON 

INSERT [dbo].[ACCOUNT] ([ID], [UserName], [pass], [avatar], [numberOfGame], [numberOfWin], [numberOfDraw], [IsOnline], [IsPlaying]) VALUES (1021, N'A         ', N'1111      ', N'0         ', 2, 0, 1, 0, 1)
INSERT [dbo].[ACCOUNT] ([ID], [UserName], [pass], [avatar], [numberOfGame], [numberOfWin], [numberOfDraw], [IsOnline], [IsPlaying]) VALUES (1022, N'B         ', N'1111      ', N'1         ', 2, 1, 1, 0, 1)
SET IDENTITY_INSERT [dbo].[ACCOUNT] OFF
GO
ALTER TABLE [dbo].[ACCOUNT] ADD  CONSTRAINT [DF_ACCOUNT_numberOfGame]  DEFAULT ((0)) FOR [numberOfGame]
GO
ALTER TABLE [dbo].[ACCOUNT] ADD  CONSTRAINT [DF_ACCOUNT_numberOfWin]  DEFAULT ((0)) FOR [numberOfWin]
GO
ALTER TABLE [dbo].[ACCOUNT] ADD  CONSTRAINT [DF_ACCOUNT_numberOfDraw]  DEFAULT ((0)) FOR [numberOfDraw]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'((0))' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ACCOUNT', @level2type=N'COLUMN',@level2name=N'numberOfWin'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'((0))' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ACCOUNT', @level2type=N'COLUMN',@level2name=N'numberOfDraw'
GO
